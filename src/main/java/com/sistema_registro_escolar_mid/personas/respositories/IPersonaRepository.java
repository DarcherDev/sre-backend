package com.sistema_registro_escolar_mid.personas.respositories;


import com.sistema_registro_escolar_mid.personas.model.PersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<PersonaModel, Long> {

}
