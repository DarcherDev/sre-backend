package com.sistema_registro_escolar_mid.estudiantes.service;

import com.sistema_registro_escolar_mid.common.responses.Responses;
import com.sistema_registro_escolar_mid.common.utilities.ValidationUtil;
import com.sistema_registro_escolar_mid.estudiantes.dto.EstudianteDto;
import com.sistema_registro_escolar_mid.estudiantes.exceptions.EstudianteException;
import com.sistema_registro_escolar_mid.estudiantes.mappers.IEstudianteMapper;
import com.sistema_registro_escolar_mid.estudiantes.model.EstudianteModel;
import com.sistema_registro_escolar_mid.estudiantes.respositories.IEstudianteRepository;
import com.sistema_registro_escolar_mid.estudiantes.utilities.EstudianteConstants;
import com.sistema_registro_escolar_mid.personas.dto.PersonaDto;
import com.sistema_registro_escolar_mid.personas.service.PersonaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de Estudiante.
 */
@Service
public class EstudianteService {

    private final IEstudianteMapper iEstudianteMapper;
    private final IEstudianteRepository iEstudianteRepository;
    private final PersonaService personaService;

    /**
     * Constructor de la clase EstudianteService.
     *
     * @param iEstudianteMapper Mapper para convertir entidades a DTOs y viceversa.
     * @param iEstudianteRepository Repositorio para el acceso a Estudiante.
     */
    public EstudianteService(
            IEstudianteMapper iEstudianteMapper,
            IEstudianteRepository iEstudianteRepository,
            PersonaService personaService
    ) {
        this.iEstudianteMapper = iEstudianteMapper;
        this.iEstudianteRepository = iEstudianteRepository;
        this.personaService = personaService;
    }
    /**
     * Obtiene todos los Estudiante activos en el sistema.
     *
     * @return Lista de {@link EstudianteDto} con los Estudiante activos.
     */
    public List<EstudianteDto> getAllEstudiantes() {
        return this.iEstudianteRepository.findAll().stream()
                .map(this.iEstudianteMapper::toEstudianteDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene un Estudiante activo por su identificador.
     *
     * @param id Identificador único del Estudiante.
     * @return {@link EstudianteDto} correspondiente al Estudiante encontrado.
     * @throws EstudianteException Si el Estudiante no existe o no está activo.
     */
    public EstudianteDto getEstudianteById(Long id) {
        EstudianteModel Estudiante = this.iEstudianteRepository.findById(id)
                .orElseThrow(() -> new EstudianteException(
                        HttpStatus.NOT_FOUND,
                        EstudianteConstants.ESTUDIANTE_NOT_FOUND
                ));

        return this.iEstudianteMapper.toEstudianteDto(Estudiante);
    }


    /**
     * Crea un nuevo estudiante en el sistema.
     *
     * @param estudianteDto Objeto {@link EstudianteDto} con los datos del Estudiante a crear.
     * @return {@link EstudianteDto} con la información del Estudiante creado.
     * @throws EstudianteException Si hay errores en la validación de los datos.
     */
    public EstudianteDto createEstudiante(EstudianteDto estudianteDto) {

        // realiza las validacion de los campos de los Estudiante
        validateEstudianteDto(estudianteDto);

        // Verificar si la matrícula ya existe
        if(iEstudianteRepository.existsByNumeroMatricula(estudianteDto.getNumeroMatricula())){
            throw new EstudianteException(
                    HttpStatus.CONFLICT,
                    EstudianteConstants.MATRICULA_DUPLICADO
            );
        }
        try {
            //intenta crea persona primero
            PersonaDto personaCreada = this.personaService.createPersona(estudianteDto.getPersona());

            estudianteDto.setPersona(personaCreada); // <- ACTUALIZA el DTO con el ID real

            EstudianteModel estudianteModel = this.iEstudianteMapper.toEstudianteModel(estudianteDto);
            estudianteModel = this.iEstudianteRepository.save(estudianteModel);

            return this.iEstudianteMapper.toEstudianteDto(estudianteModel);
        } catch (DataIntegrityViolationException ex) {
            throw new EstudianteException(
                    HttpStatus.CONFLICT,
                    EstudianteConstants.MATRICULA_DUPLICADO
            );
        }
    }

    /**
     *
     * @param id Identificador único del Estudiante a desactivar.
     * @return Mapa con un mensaje de confirmación de la operación.
     * @throws EstudianteException Si el Estudiante con el ID proporcionado no existe.
     */
    public Map<String, Object> deleteEstudiante(Long id) {

        EstudianteModel existingEstudiante = this.iEstudianteRepository.findById(id)
                .orElseThrow(() -> new EstudianteException(
                        HttpStatus.NOT_FOUND,
                        EstudianteConstants.ESTUDIANTE_NOT_FOUND
                ));

        this.iEstudianteRepository.delete(existingEstudiante);

        this.personaService.deletePersona(id);

        return Responses.createMapResponse.apply("Estudiante eliminada correctamente.");

    }

    /**
     * Actualiza la información de un Estudiante existente.
     *
     * @param estudianteDto Objeto {@link EstudianteDto} con los datos actualizados del Estudiante.
     * @return {@link Optional} que contiene el {@link EstudianteDto} actualizado si la operación es exitosa.
     * @throws EstudianteException Si el Estudiante con el ID proporcionado no existe.
     */
    public Optional<EstudianteDto> updateEstudiante(EstudianteDto estudianteDto) {

        // realiza las validacion de los campos de los Estudiante
        validateEstudianteDto(estudianteDto);

        //optiene lista de matricula
        Optional<EstudianteModel> estudianteConMismaMatricula = iEstudianteRepository
                .findByNumeroMatricula(estudianteDto.getNumeroMatricula());

        //valida que la matricula no este en otro estudiante
        if(estudianteConMismaMatricula.isPresent() &&
        !estudianteConMismaMatricula.get().getId().equals(estudianteDto.getId())) {
            throw new EstudianteException(
                    HttpStatus.CONFLICT,
                    EstudianteConstants.MATRICULA_DUPLICADO
            );
        }
        try {
            // Actualiza primero la persona y actualiza el DTO con el ID correcto
            PersonaDto personaActualizada = this.personaService.updatePersona(estudianteDto.getPersona())
                    .orElseThrow(() -> new EstudianteException(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            EstudianteConstants.PERSONA_NOT_FOUND
                    ));
            estudianteDto.setPersona(personaActualizada);

            // Valida que el estudiante exista
            EstudianteModel existingEstudiante = this.iEstudianteRepository.findById(estudianteDto.getId())
                    .orElseThrow(() -> new EstudianteException(
                            HttpStatus.NOT_FOUND,
                            EstudianteConstants.ESTUDIANTE_NOT_FOUND
                    ));

            // Mapea y guarda el estudiante
            EstudianteModel updatedEstudiante = this.iEstudianteMapper.toEstudianteModel(estudianteDto);
            updatedEstudiante = this.iEstudianteRepository.save(updatedEstudiante);

            return Optional.of(this.iEstudianteMapper.toEstudianteDto(updatedEstudiante));

        }catch (DataIntegrityViolationException ex) {
            throw new EstudianteException(
                    HttpStatus.CONFLICT,
                    EstudianteConstants.MATRICULA_DUPLICADO
            );
        }
    }

    /**
     * Valida los datos del Estudiante antes de su creación o actualización.
     *
     * @param EstudianteDto Objeto {@link EstudianteDto} que contiene la información del Estudiante a validar.
     * @throws EstudianteException Si los campos nombre, apellido, fecha de nacimiento, telefono y email está vacía o nula.
     */
    private void validateEstudianteDto(EstudianteDto EstudianteDto) {

        if (ValidationUtil.isNullOrEmpty(EstudianteDto.getGrado())) {
            throw new EstudianteException(HttpStatus.PRECONDITION_FAILED, EstudianteConstants.GRADO_REQUERIDO);
        }
        if (ValidationUtil.isNullOrEmpty(EstudianteDto.getNumeroMatricula())) {
            throw new EstudianteException(HttpStatus.PRECONDITION_FAILED, EstudianteConstants.MATRICULA_REQUERIDA);
        }
    }
}
