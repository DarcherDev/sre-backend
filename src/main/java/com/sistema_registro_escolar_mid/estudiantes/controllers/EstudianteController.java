package com.sistema_registro_escolar_mid.estudiantes.controllers;


import com.sistema_registro_escolar_mid.estudiantes.dto.EstudianteDto;
import com.sistema_registro_escolar_mid.estudiantes.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("estudiante")
@Tag(name = "Estudiante", description = "APIs para la gestión de Estudiantes")
public class EstudianteController {

    private final EstudianteService EstudianteService;

    public EstudianteController(EstudianteService EstudianteService) {
        this.EstudianteService = EstudianteService;
    }

    @Operation(
            summary = "Obtener todos los Estudiante activos",
            description = "Recupera una lista de Estudiante activos.",
            tags = { "GET" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Éxito", content = @Content(schema = @Schema(implementation = EstudianteDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/all")
    public ResponseEntity<List<EstudianteDto>> getAllEstudiantes() {
        List<EstudianteDto> estudiantes = this.EstudianteService.getAllEstudiantes();
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener Estudiante activo por ID",
            description = "Recupera un Estudiante activo especificando su ID.",
            tags = { "GET" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Éxito", content = @Content(schema = @Schema(implementation = EstudianteDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/")
    public ResponseEntity<EstudianteDto> getEstudianteById(@RequestParam(name = "EstudianteId") Long EstudianteId) {
        EstudianteDto EstudianteDto = this.EstudianteService.getEstudianteById(EstudianteId);
        return new ResponseEntity<>(EstudianteDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Crear un nuevo Estudiante",
            description = "Crea un nuevo Estudiante y devuelve los detalles del Estudiante creado.",
            tags = { "POST" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Creado", content = @Content(schema = @Schema(implementation = EstudianteDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "412", description = "Error de formato en la solicitud"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/create")
    public ResponseEntity<EstudianteDto> createEstudiante(@RequestBody EstudianteDto EstudianteDto) {
        EstudianteDto createdEstudiante = this.EstudianteService.createEstudiante(EstudianteDto);
        return new ResponseEntity<>(createdEstudiante, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Desactivar un Estudiante",
            description = "Desactiva un Estudiante estableciendo su estado como inactivo.",
            tags = { "DELETE" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudiante desactivado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteEstudiante(@PathVariable Long id) {
        Map<String, Object> mapResponse = this.EstudianteService.deleteEstudiante(id);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Actualizar un Estudiante",
            description = "Actualiza los detalles de un Estudiante existente.",
            tags = { "PUT" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actualizado exitosamente", content = @Content(schema = @Schema(implementation = EstudianteDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/update")
    public ResponseEntity<Optional<EstudianteDto>> updateEstudiante(@RequestBody EstudianteDto EstudianteDto) {
        Optional<EstudianteDto> updatedEstudiante = this.EstudianteService.updateEstudiante(EstudianteDto);
        return new ResponseEntity<>(updatedEstudiante, HttpStatus.OK);
    }

}
