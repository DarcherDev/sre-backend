package com.sistema_registro_escolar_mid.personas.service;

import com.sistema_registro_escolar_mid.common.responses.Responses;
import com.sistema_registro_escolar_mid.common.utilities.ValidationUtil;
import com.sistema_registro_escolar_mid.personas.dto.PersonaDto;
import com.sistema_registro_escolar_mid.personas.exceptions.PersonaException;
import com.sistema_registro_escolar_mid.personas.mappers.IPersonaMapper;
import com.sistema_registro_escolar_mid.personas.model.PersonaModel;
import com.sistema_registro_escolar_mid.personas.respositories.IPersonaRepository;
import com.sistema_registro_escolar_mid.personas.utilities.PersonaConstants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * Servicio para la gestión de persona.
 */
@Service
public class PersonaService {

    private final IPersonaMapper iPersonaMapper;
    private final IPersonaRepository iPersonaRepository;

    /**
     * Constructor de la clase PersonaService.
     *
     * @param iPersonaMapper Mapper para convertir entidades a DTOs y viceversa.
     * @param iPersonaRepository Repositorio para el acceso a persona.
     */
    public PersonaService(
            IPersonaMapper iPersonaMapper,
            IPersonaRepository iPersonaRepository
    ) {
        this.iPersonaMapper = iPersonaMapper;
        this.iPersonaRepository = iPersonaRepository;
    }

    /**
     * Crea un nuevo persona en el sistema.
     *
     * @param personaDto Objeto {@link PersonaDto} con los datos del persona a crear.
     * @return {@link PersonaDto} con la información del persona creado.
     * @throws PersonaException Si hay errores en la validación de los datos.
     */
    public PersonaDto createPersona(PersonaDto personaDto) {
        try {
            // realiza las validacion de los campos de los persona
            validatePersonaDto(personaDto);

            // generar el modelo del persona
            PersonaModel personaModel = this.iPersonaMapper.toPersonaModel(personaDto);

            // guarda el persona
            personaModel = this.iPersonaRepository.save(personaModel);

            return this.iPersonaMapper.toPersonaDto(personaModel);
        }catch (DataIntegrityViolationException ex) {
            // Captura específica para violación de constraints (como @Unique)
            throw new PersonaException(
                    HttpStatus.CONFLICT,
                    PersonaConstants.CORREO_DUPLICADO
            );
        }
    }

    /**
     *
     * @param id Identificador único del persona a desactivar.
     * @return Mapa con un mensaje de confirmación de la operación.
     * @throws PersonaException Si el persona con el ID proporcionado no existe.
     */
    public Map<String, Object> deletePersona(Long id) {

        PersonaModel existingPersona = this.iPersonaRepository.findById(id)
                .orElseThrow(() -> new PersonaException(
                        HttpStatus.NOT_FOUND,
                        PersonaConstants.PERSONA_NOT_FOUND
                ));

        this.iPersonaRepository.delete(existingPersona);

        return Responses.createMapResponse.apply("Persona eliminada correctamente.");
    }

    /**
     * Actualiza la información de un persona existente.
     *
     * @param personaDto Objeto {@link PersonaDto} con los datos actualizados del persona.
     * @return {@link Optional} que contiene el {@link PersonaDto} actualizado si la operación es exitosa.
     * @throws PersonaException Si el persona con el ID proporcionado no existe.
     */
    public Optional<PersonaDto> updatePersona(PersonaDto personaDto) {

        // realiza las validacion de los campos de los persona
        validatePersonaDto(personaDto);

        PersonaModel existingPersona = this.iPersonaRepository.findById(personaDto.getId())
                .orElseThrow(() -> new PersonaException(
                        HttpStatus.NOT_FOUND,
                        PersonaConstants.PERSONA_NOT_FOUND
                ));

        PersonaModel updatedPersona = this.iPersonaMapper.toPersonaModel(personaDto);

        // actualiza el persona
        updatedPersona = this.iPersonaRepository.save(updatedPersona);

        return Optional.of(this.iPersonaMapper.toPersonaDto(updatedPersona));
    }

    /**
     * Valida los datos del persona antes de su creación o actualización.
     *
     * @param PersonaDto Objeto {@link PersonaDto} que contiene la información del persona a validar.
     * @throws PersonaException Si los campos nombre, apellido, fecha de nacimiento, telefono y email está vacía o nula.
     */
    private void validatePersonaDto(PersonaDto PersonaDto) {

        if (ValidationUtil.isNullOrEmpty(PersonaDto.getFirstName())) {
            throw new PersonaException(HttpStatus.PRECONDITION_FAILED, PersonaConstants.NOMBRE_REQUERIDO);
        }
        if (ValidationUtil.isNullOrEmpty(PersonaDto.getLastNames())) {
            throw new PersonaException(HttpStatus.PRECONDITION_FAILED, PersonaConstants.APELLIDO_REQUERIDO);
        }
        if (ValidationUtil.isNullOrEmpty(PersonaDto.getBirthDate())) {
            throw new PersonaException(HttpStatus.PRECONDITION_FAILED, PersonaConstants.FECHA_NACIMIENTO_REQUERIDO);
        }
        if (ValidationUtil.esFechaValida(PersonaDto.getBirthDate())) {
            throw new PersonaException(HttpStatus.PRECONDITION_FAILED, PersonaConstants.FORMATO_FECHA);
        }
        if (ValidationUtil.isNullOrEmpty(PersonaDto.getEmail())) {
            throw new PersonaException(HttpStatus.PRECONDITION_FAILED, PersonaConstants.CORREO_REQUERIDO);
        }
        if (ValidationUtil.validateEmail(PersonaDto.getEmail())) {
            throw new PersonaException(HttpStatus.PRECONDITION_FAILED, PersonaConstants.FORMATO_CORREO);
        }
        if (!ValidationUtil.esNumeroValido(PersonaDto.getPhone())) {
            throw new PersonaException(HttpStatus.PRECONDITION_FAILED, PersonaConstants.FORMATO_TELEFONO);
        }

    }
}
