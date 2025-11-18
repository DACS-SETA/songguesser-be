import tkinter as tk
from tkinter import ttk, messagebox
import requests
import json

class ITunesDiggerApp:
    def __init__(self, root):
        self.root = root
        self.root.title("iTunes SQL Digger 🎵 (Auto ID)")
        self.root.geometry("900x700")
        
        # Variables de control
        self.search_var = tk.StringVar()
        self.country_var = tk.StringVar(value="AR")
        
        # --- SECCIÓN SUPERIOR: BÚSQUEDA ---
        top_frame = ttk.LabelFrame(root, text="Buscador", padding="10")
        top_frame.pack(fill="x", padx=10, pady=5)
        
        # Grid layout para el top frame
        ttk.Label(top_frame, text="País Store:").grid(row=0, column=0, padx=5, sticky="w")
        country_combo = ttk.Combobox(top_frame, textvariable=self.country_var, width=5, state="readonly")
        country_combo['values'] = ('AR', 'US', 'ES', 'MX', 'CL', 'GB', 'BR')
        country_combo.grid(row=0, column=1, padx=5)
        
        ttk.Label(top_frame, text="Canción / Artista:").grid(row=0, column=2, padx=5, sticky="w")
        self.entry_search = ttk.Entry(top_frame, textvariable=self.search_var, width=40)
        self.entry_search.grid(row=0, column=3, padx=5)
        self.entry_search.bind('<Return>', self.perform_search) # Enter para buscar
        
        btn_search = ttk.Button(top_frame, text="Buscar 🔍", command=self.perform_search)
        btn_search.grid(row=0, column=4, padx=5)

        # --- SECCIÓN MEDIA: RESULTADOS ---
        mid_frame = ttk.LabelFrame(root, text="Resultados (Doble Click para agregar)", padding="10")
        mid_frame.pack(fill="both", expand=True, padx=10, pady=5)
        
        # Treeview para la tabla
        columns = ('artist', 'track', 'album', 'id', 'date')
        self.tree = ttk.Treeview(mid_frame, columns=columns, show='headings')
        
        # Definir encabezados
        self.tree.heading('artist', text='Artista')
        self.tree.heading('track', text='Canción')
        self.tree.heading('album', text='Álbum')
        self.tree.heading('id', text='iTunes ID')
        self.tree.heading('date', text='Año')
        
        # Definir columnas
        self.tree.column('artist', width=150)
        self.tree.column('track', width=200)
        self.tree.column('album', width=150)
        self.tree.column('id', width=80)
        self.tree.column('date', width=50)
        
        # Scrollbar
        scrollbar = ttk.Scrollbar(mid_frame, orient=tk.VERTICAL, command=self.tree.yview)
        self.tree.configure(yscroll=scrollbar.set)
        
        self.tree.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        
        # Evento doble click
        self.tree.bind("<Double-1>", self.add_to_log)

        # --- SECCIÓN INFERIOR: LOG SQL ---
        bottom_frame = ttk.LabelFrame(root, text="Log SQL Generado", padding="10")
        bottom_frame.pack(fill="both", expand=True, padx=10, pady=5)
        
        # Botonera inferior
        btn_frame = ttk.Frame(bottom_frame)
        btn_frame.pack(fill="x", pady=5)
        ttk.Button(btn_frame, text="Copiar Todo", command=self.copy_to_clipboard).pack(side=tk.RIGHT)
        ttk.Button(btn_frame, text="Limpiar Log", command=self.clear_log).pack(side=tk.RIGHT, padx=5)

        # Text Area
        self.log_text = tk.Text(bottom_frame, height=10, font=("Consolas", 9))
        self.log_text.pack(fill="both", expand=True)

    def perform_search(self, event=None):
        term = self.search_var.get()
        country = self.country_var.get()
        
        if not term:
            return

        # Limpiar tabla anterior
        for item in self.tree.get_children():
            self.tree.delete(item)
            
        url = "https://itunes.apple.com/search"
        params = {
            "term": term,
            "media": "music",
            "entity": "song",
            "limit": 25,
            "country": country
        }
        
        try:
            response = requests.get(url, params=params)
            data = response.json()
            
            if data['resultCount'] == 0:
                messagebox.showinfo("Info", f"No se encontraron resultados en el store de {country}.")
                return

            for track in data['results']:
                # Extraer año
                year = track.get('releaseDate', '')[:4]
                
                self.tree.insert('', tk.END, values=(
                    track.get('artistName', 'Unknown'),
                    track.get('trackName', 'Unknown'),
                    track.get('collectionName', 'Unknown'),
                    track.get('trackId', 0),
                    year
                ))
                
        except Exception as e:
            messagebox.showerror("Error", f"Error de conexión: {e}")

    def add_to_log(self, event):
        # Obtener item seleccionado
        selected_item = self.tree.selection()
        if not selected_item:
            return
        
        item = self.tree.item(selected_item)
        values = item['values']
        
        artist = values[0].replace("'", "''") # Escapar comillas simples para SQL
        track = values[1].replace("'", "''")
        itunes_id = values[3]
        
        # Lógica simple de idioma basada en el store seleccionado
        # (No es perfecta, pero sirve para clasificar rápido)
        country = self.country_var.get()
        if country in ['AR', 'ES', 'MX', 'CL']:
            lang = 'es'
        elif country == 'BR':
            lang = 'pt' # Agregué BR por si acaso
        else:
            lang = 'en'
        
        # Formato SQL SIN ID explícito
        sql_query = f"INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('{artist}', '{track}', {itunes_id}, '{lang}');\n"
        
        self.log_text.insert(tk.END, sql_query)
        self.log_text.see(tk.END) # Auto scroll al final

    def copy_to_clipboard(self):
        self.root.clipboard_clear()
        self.root.clipboard_append(self.log_text.get("1.0", tk.END))
        messagebox.showinfo("Éxito", "SQL copiado al portapapeles")

    def clear_log(self):
        self.log_text.delete("1.0", tk.END)

if __name__ == "__main__":
    root = tk.Tk()
    app = ITunesDiggerApp(root)
    root.mainloop()