package com.sistema_registro_escolar_mid.profesores.respositories;


import com.sistema_registro_escolar_mid.profesores.model.ProfesorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProfesorRepository extends JpaRepository<ProfesorModel, Long> {

}
