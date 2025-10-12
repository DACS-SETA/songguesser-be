package com.songguesser.backend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.songguesser.backend.model.entity.Alumno;



@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long>{

}
