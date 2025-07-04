package com.sistema_registro_escolar_mid.estudiantes.respositories;


import com.sistema_registro_escolar_mid.estudiantes.model.EstudianteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEstudianteRepository extends JpaRepository<EstudianteModel, Long> {
    boolean existsByNumeroMatricula(String numeroMatricula);
    Optional<EstudianteModel> findByNumeroMatricula(String numeroMatricula);
}
