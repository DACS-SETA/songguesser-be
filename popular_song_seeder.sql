-- =============================================
-- 1. LIMPIEZA DE DATOS (Orden correcto por FK)
-- =============================================

-- Primero borrar tablas dependientes (Hijas)
DELETE FROM public.round;
DELETE FROM public.game;

-- Luego borrar la tabla principal (Padre)
DELETE FROM public.song;

-- =============================================
-- 2. REINICIO DE SECUENCIAS
-- =============================================

-- Como la tabla quedó vacía, reiniciamos desde 1 para que los IDs queden ordenados
ALTER SEQUENCE public.song_id_seq RESTART WITH 1;
ALTER SEQUENCE public.game_id_seq RESTART WITH 1;
ALTER SEQUENCE public.round_id_seq RESTART WITH 1;

-- =============================================
-- 3. INSERCIÓN DE DATOS (Ids automáticos)
-- =============================================

-- --- INTERNACIONAL (EN/MIXED) ---

-- Taylor Swift
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Taylor Swift', 'Cruel Summer', 1468058171, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Taylor Swift', 'Shake It Off', 1440936016, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Taylor Swift', 'Love Story', 1452859410, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Taylor Swift', 'You Belong With Me', 1452859427, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Taylor Swift', 'Blank Space', 1440935808, 'en');

-- The Weeknd / Ariana
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Ariana Grande & The Weeknd', 'off the table (live from Vevo)', 1849204396, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('The Weeknd', 'Call Out My Name', 1363310482, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('The Weeknd', 'Blinding Lights', 1488408568, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('The Weeknd', 'Die For You', 1440872304, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('The Weeknd, JENNIE & Lily Rose Depp', 'One Of The Girls', 1693891139, 'en');

-- Drake
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Drake', 'Hold On, We''re Going Home (feat. Majid Jordan)', 1440829630, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Drake', 'One Dance (feat. Wizkid & Kyla)', 1440843496, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Drake', 'Hotline Bling', 1440841730, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Still Woozy', 'Drake', 1572737688, 'en');

-- Adele
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Adele', 'Someone Like You', 1544491998, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Adele', 'Set Fire to the Rain', 1544491988, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Adele', 'Rolling in the Deep', 1544491233, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Adele', 'Easy On Me', 1590036021, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Adele', 'Hello', 1544494392, 'en');

-- Ed Sheeran
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Ed Sheeran', 'Perfect', 1193701400, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Ed Sheeran', 'Thinking Out Loud', 1050204631, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Ed Sheeran', 'Shape of You', 1193701392, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Ed Sheeran', 'Shivers', 1581087034, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Ed Sheeran', 'Photograph', 1050204626, 'en');

-- Beyoncé
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Beyoncé', 'Dance For You', 626205222, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Beyoncé', 'Irreplaceable', 261707067, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Beyoncé', 'Best Thing I Never Had', 626205875, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Beyoncé', 'Love On Top', 626205216, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Beyoncé', '1+1', 626205872, 'en');

-- Coldplay
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Coldplay', 'Yellow', 1122782283, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Coldplay', 'Sparks', 1122782281, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Coldplay', 'Viva La Vida', 1122773680, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Coldplay', 'A Sky Full of Stars', 829910927, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Coldplay', 'The Scientist', 1122776155, 'en');

-- Billie Eilish
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Billie Eilish', 'WILDFLOWER', 1739659144, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Billie Eilish', 'BIRDS OF A FEATHER', 1739659142, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Billie Eilish', 'ocean eyes', 1440899467, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Billie Eilish & Khalid', 'lovely', 1369380479, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Billie Eilish', 'What Was I Made For? (From The Motion Picture "Barbie")', 1696819855, 'en');

-- Bruno Mars
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Lady Gaga & Bruno Mars', 'Die With A Smile', 1762656732, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Bruno Mars', 'When I Was Your Man', 573962555, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Bruno Mars', '24K Magic', 1161504024, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Mark Ronson', 'Uptown Funk (feat. Bruno Mars)', 943946671, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Bruno Mars', 'That''s What I Like', 1161504043, 'en');

-- Rihanna
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Rihanna', 'Umbrella (feat. JAY-Z)', 1441154437, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Rihanna', 'Don''t Stop The Music', 1441154439, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Rihanna', 'Only Girl (In The World)', 1440896009, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Rihanna', 'Diamonds', 1444321282, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Rihanna', 'Disturbia', 1441154571, 'en');

-- Post Malone
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Post Malone', 'Circles', 1477880561, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Post Malone & Swae Lee', 'Sunflower (Spider-Man: Into the Spider-Verse)', 1445949267, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Morgan Wallen & Post Malone', 'I Ain''t Comin'' Back', 1802104420, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Post Malone', 'Pour Me A Drink (feat. Blake Shelton)', 1752531909, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Sam Feldt', 'Post Malone (feat. RANI)', 1462755255, 'en');

-- Eminem
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Eminem', 'Lose Yourself', 1440903439, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Eminem', 'Rap God', 1440864098, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Eminem', 'Godzilla (feat. Juice WRLD)', 1495269629, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Eminem', 'Without Me', 1440821643, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Joeyy', 'Eminem', 1698162327, 'en');

-- Shakira
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Shakira', 'Hips Don''t Lie (feat. Wyclef Jean)', 1817217063, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Shakira', 'Whenever, Wherever', 188261029, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Shakira', 'She Wolf', 340234622, 'en');

-- Imagine Dragons
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Imagine Dragons', 'Thunder', 1411629089, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Imagine Dragons', 'Believer', 1411628233, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Imagine Dragons', 'Radioactive', 1440873126, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Imagine Dragons', 'Demons', 1440873339, 'en');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Imagine Dragons', 'Whatever It Takes', 1411628148, 'en');


-- --- MÚSICA ARGENTINA / LATINA (ES) ---

-- Charly García & Serú Girán
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Charly Garcia', 'No Voy en Tren', 321800205, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Charly Garcia', 'No Me Dejan Salir', 1443921380, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Charly Garcia', 'Rezo Por Vos', 321800209, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Charly Garcia & Pedro Aznar', 'Hablando a Tu Corazón', 321731214, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Charly Garcia', 'Promesas Sobre El Bidet', 1421884387, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Charly Garcia', 'Los Dinosaurios', 1443921381, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Charly Garcia', 'Chipi-Chipi', 321692069, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Charly Garcia', 'Buscando un Símbolo de Paz', 321800185, 'es');

-- Luis Alberto Spinetta / Almendra
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Luis Alberto Spinetta', 'Seguir Viviendo Sin Tu Amor', 723583764, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Luis Alberto Spinetta', 'Barro Tal Vez', 1091406776, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Almendra', 'Muchacha (Ojos de Papel)', 410017756, 'es');

-- Soda Stereo
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Soda Stereo', 'De Música Ligera', 321882958, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Soda Stereo', 'Cuando Pase el Temblor', 321883388, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Soda Stereo', 'Signos', 321882523, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Soda Stereo', 'Ella Usó Mi Cabeza Como un Revólver', 309452445, 'es');

-- Gustavo Cerati
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Gustavo Cerati', 'Adiós', 157364223, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Gustavo Cerati', 'Crimen', 157364342, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Gustavo Cerati', 'Puente', 304793008, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Gustavo Cerati', 'Amor Amarillo', 325221768, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Gustavo Cerati', 'Deja Vu', 1358493169, 'es');

-- Patricio Rey y sus Redonditos de Ricota
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Patricio Rey y sus Redonditos de Ricota', 'La Bestia Pop', 1722367048, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Patricio Rey y sus Redonditos de Ricota', 'Ji Ji Ji', 1259849414, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Patricio Rey y sus Redonditos de Ricota', 'Un Poco de Amor Francés', 1722348398, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Patricio Rey y sus Redonditos de Ricota', 'Un Ángel para Tu Soledad', 1722371225, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Patricio Rey y sus Redonditos de Ricota', 'Mi Perro Dinamita', 1722348399, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Patricio Rey y sus Redonditos de Ricota', 'Esa Estrella Era Mi Lujo', 1722340001, 'es');

-- Fito Páez
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'El Amor Después del Amor', 201571436, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'Mariposa Tecknicolor', 79035349, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', '11 y 6', 723790139, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'Al Lado del Camino', 79034264, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'A Rodar Mi Vida', 201572459, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'Fue Amor', 201533705, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'Brillante Sobre el Mic', 201572257, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'La Rueda Mágica', 201571933, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'Giros', 723789997, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'Cable a Tierra', 723790185, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'Un Vestido y un Amor', 201571895, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Fito Páez', 'Yo Vengo a Ofrecer Mi Corazón', 723790148, 'es');

-- Andrés Calamaro / Los Abuelos
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Andrés Calamaro', 'Flaca', 101374818, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Andrés Calamaro', 'Loco', 101374705, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Andrés Calamaro', 'Te Quiero Igual', 101050742, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Andrés Calamaro', 'Crimenes Perfectos', 101375334, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Andrés Calamaro', 'Cuando Te Conocí', 101051033, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Abuelos de la Nada', 'Mil Horas (1994 Remastered Version)', 1723909257, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Abuelos de la Nada', 'Lunes Por la Madrugada', 1443318299, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Abuelos de la Nada', 'Costumbres Argentinas (1994 Remastered Version)', 1723909393, 'es');

-- Fabulosos Cadillacs / Auténticos Decadentes
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Fabulosos Cadillacs', 'Vasos Vacíos', 297921318, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Fabulosos Cadillacs', 'Siguiendo la Luna', 297921314, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Fabulosos Cadillacs', 'Matador', 297921308, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Fabulosos Cadillacs', 'Manuel Santillán, el León (Versión Reggae)', 297921315, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Auténticos Decadentes', 'Loco (Tu Forma de Ser)', 254001628, 'es');

-- Virus
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Virus', 'Pronta Entrega', 470515900, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Virus', 'Luna de Miel en la Mano', 470515930, 'es');

-- Los Piojos
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Piojos', 'Tan Solo', 1613774320, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Piojos', 'Verano del 92', 1613766864, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Piojos', 'Bicho de Ciudad', 1811624413, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Piojos', 'El Farolito', 1613766187, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Los Piojos', 'Ando Ganas (Llora Llora)', 1613768336, 'es');

-- Babasónicos
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Babasónicos', 'Como Eran las Cosas', 1443195841, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Babasónicos', 'Irresponsables', 403659455, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Babasónicos', 'El Loco', 403461399, 'es');

-- Miranda!
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Miranda!', 'Prisionero', 1043848309, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Miranda!', 'Don', 1043847390, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Miranda!', 'Tu Misterioso Alguien', 1044480392, 'es');

-- Tan Biónica
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Tan Bionica', 'Ciudad Mágica', 1811680157, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Tan Bionica', 'Obsesionario en La Mayor', 1811678315, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Tan Bionica', 'La Melodía de Dios', 1811680159, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Tan Bionica', 'Loca', 1811678319, 'es');

-- Airbag
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Airbag', 'Por Mil Noches', 1755430345, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Airbag', 'Cae el Sol', 1755458564, 'es');

-- Rodrigo & Gilda (Cuarteto/Cumbia)
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Rodrigo', 'Soy Cordobés', 470395131, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Rodrigo', 'Lo mejor del amor', 404517013, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Gilda', 'No Me Arrepiento de Este Amor', 497493349, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Gilda', 'Paisaje', 305925067, 'es');

-- Urbano (TINI, Emilia, Maria Becerra)
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('TINI', 'Cupido', 1668892944, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('TINI & Maria Becerra', 'Miénteme', 1668894208, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Emilia, TINI & NICKI NICOLE', 'blackout 🧊', 1803493549, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Emilia', 'La_Original.mp3', 1712009245, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('Emilia & Tiago PZK', 'rápido lento', 1586913812, 'es');
INSERT INTO public.song (artist_name, track_name, itunes_song_id, language) VALUES ('TINI', 'La Triple T', 1668894411, 'es');