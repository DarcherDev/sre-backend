package com.sistema_registro_escolar_mid.profesores.service;

import com.sistema_registro_escolar_mid.common.responses.Responses;
import com.sistema_registro_escolar_mid.common.utilities.ValidationUtil;
import com.sistema_registro_escolar_mid.personas.dto.PersonaDto;
import com.sistema_registro_escolar_mid.personas.service.PersonaService;
import com.sistema_registro_escolar_mid.profesores.dto.ProfesorDto;
import com.sistema_registro_escolar_mid.profesores.exceptions.ProfesorException;
import com.sistema_registro_escolar_mid.profesores.mappers.IProfesorMapper;
import com.sistema_registro_escolar_mid.profesores.model.ProfesorModel;
import com.sistema_registro_escolar_mid.profesores.respositories.IProfesorRepository;
import com.sistema_registro_escolar_mid.profesores.utilities.ProfesorConstants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de Profesor.
 */
@Service
public class ProfesorService {

    private final IProfesorMapper iProfesorMapper;
    private final IProfesorRepository iProfesorRepository;
    private final PersonaService personaService;

    /**
     * Constructor de la clase ProfesorService.
     *
     * @param iProfesorMapper Mapper para convertir entidades a DTOs y viceversa.
     * @param iProfesorRepository Repositorio para el acceso a Profesor.
     */
    public ProfesorService(
            IProfesorMapper iProfesorMapper,
            IProfesorRepository iProfesorRepository,
            PersonaService personaService
    ) {
        this.iProfesorMapper = iProfesorMapper;
        this.iProfesorRepository = iProfesorRepository;
        this.personaService = personaService;
    }
    /**
     * Obtiene todos los Profesor activos en el sistema.
     *
     * @return Lista de {@link ProfesorDto} con los Profesor activos.
     */
    public List<ProfesorDto> getAllProfesores() {
        return this.iProfesorRepository.findAll().stream()
                .map(this.iProfesorMapper::toProfesorDto)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un Profesor activo por su identificador.
     *
     * @param id Identificador único del Profesor.
     * @return {@link ProfesorDto} correspondiente al Profesor encontrado.
     * @throws ProfesorException Si el Profesor no existe o no está activo.
     */
    public ProfesorDto getProfesorById(Long id) {
        ProfesorModel Profesor = this.iProfesorRepository.findById(id)
                .orElseThrow(() -> new ProfesorException(
                        HttpStatus.NOT_FOUND,
                        ProfesorConstants.PROFESOR_NOT_FOUND
                ));
        return this.iProfesorMapper.toProfesorDto(Profesor);
    }

    /**
     * Crea un nuevo Profesor en el sistema.
     *
     * @param profesorDto Objeto {@link ProfesorDto} con los datos del Profesor a crear.
     * @return {@link ProfesorDto} con la información del Profesor creado.
     * @throws ProfesorException Si hay errores en la validación de los datos.
     */
    public ProfesorDto createProfesor(ProfesorDto profesorDto) {

        // realiza las validacion de los campos de los Profesores
        validateProfesorDto(profesorDto);

        //intenta crea persona primero
        PersonaDto personaCreada = this.personaService.createPersona(profesorDto.getPersona());

        profesorDto.setPersona(personaCreada); // <- ACTUALIZA el DTO con el ID real

        ProfesorModel profesorModel = this.iProfesorMapper.toProfesorModel(profesorDto);
        profesorModel = this.iProfesorRepository.save(profesorModel);

        return this.iProfesorMapper.toProfesorDto(profesorModel);
    }

    /**
     *
     * @param id Identificador único del Profesor a desactivar.
     * @return Mapa con un mensaje de confirmación de la operación.
     * @throws ProfesorException Si el Profesor con el ID proporcionado no existe.
     */
    public Map<String, Object> deleteProfesor(Long id) {

        ProfesorModel existingProfesor = this.iProfesorRepository.findById(id)
                .orElseThrow(() -> new ProfesorException(
                        HttpStatus.NOT_FOUND,
                        ProfesorConstants.PROFESOR_NOT_FOUND
                ));

        this.iProfesorRepository.delete(existingProfesor);

        this.personaService.deletePersona(id);

        return Responses.createMapResponse.apply("Profesor eliminada correctamente.");

    }

    /**
     * Actualiza la información de un profesor existente.
     *
     * @param profesorDto Objeto {@link ProfesorDto} con los datos actualizados del Estudiante.
     * @return {@link Optional} que contiene el {@link ProfesorDto} actualizado si la operación es exitosa.
     * @throws ProfesorException Si el Estudiante con el ID proporcionado no existe.
     */
    public Optional<ProfesorDto> updateProfesor(ProfesorDto profesorDto) {

        // realiza las validacion de los campos de los Estudiante
        validateProfesorDto(profesorDto);

         try {
            // Actualiza primero la persona y actualiza el DTO con el ID correcto
            PersonaDto personaActualizada = this.personaService.updatePersona(profesorDto.getPersona())
                    .orElseThrow(() -> new ProfesorException(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            ProfesorConstants.PERSONA_NOT_FOUND
                    ));
            profesorDto.setPersona(personaActualizada);

            // Valida que el profesor exista
             ProfesorModel existingProfesor = this.iProfesorRepository.findById(profesorDto.getId())
                     .orElseThrow(() -> new ProfesorException(
                             HttpStatus.NOT_FOUND,
                             ProfesorConstants.PROFESOR_NOT_FOUND
                     ));

            // Mapea y guarda el profesor
            ProfesorModel updatedProfesor = this.iProfesorMapper.toProfesorModel(profesorDto);
            updatedProfesor = this.iProfesorRepository.save(updatedProfesor);

            return Optional.of(this.iProfesorMapper.toProfesorDto(updatedProfesor));

        }catch (DataIntegrityViolationException ex) {
            throw new ProfesorException(
                    HttpStatus.CONFLICT,
                    ProfesorConstants.PROFESOR_NOT_FOUND
            );
        }
    }

    /**
     * Valida los datos del Profesor antes de su creación o actualización.
     *
     * @param ProfesorDto Objeto {@link ProfesorDto} que contiene la información del Profesor a validar.
     * @throws ProfesorException Si los campos nombre, apellido, fecha de nacimiento, telefono y email está vacía o nula.
     */
    private void validateProfesorDto(ProfesorDto ProfesorDto) {

        if (ValidationUtil.isNullOrEmpty(ProfesorDto.getEspecialidad())) {
            throw new ProfesorException(HttpStatus.PRECONDITION_FAILED, ProfesorConstants.ESPECIALIDAD_REQUERIDA);
        }
        if (ValidationUtil.isNullOrEmpty(ProfesorDto.getContratacionFecha())) {
            throw new ProfesorException(HttpStatus.PRECONDITION_FAILED, ProfesorConstants.FECHA_CONTRATACION_REQUERIDAD);
        }
    }
}
