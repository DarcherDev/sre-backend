package com.sistema_registro_escolar_mid.profesores.controllers;


import com.sistema_registro_escolar_mid.profesores.dto.ProfesorDto;
import com.sistema_registro_escolar_mid.profesores.service.ProfesorService;
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
@RequestMapping("profesores")
@Tag(name = "Profesor", description = "APIs para la gestión de Profesor")
public class ProfesorController {

    private final ProfesorService ProfesorService;

    public ProfesorController(ProfesorService ProfesorService) {
        this.ProfesorService = ProfesorService;
    }

    @Operation(
            summary = "Obtener todos los Profesor activos",
            description = "Recupera una lista de Profesor activos.",
            tags = { "GET" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Éxito", content = @Content(schema = @Schema(implementation = ProfesorDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/all")
    public ResponseEntity<List<ProfesorDto>> getAllProfesor() {
        List<ProfesorDto> Profesor = this.ProfesorService.getAllProfesores();
        return new ResponseEntity<>(Profesor, HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener Profesor activo por ID",
            description = "Recupera un Profesor activo especificando su ID.",
            tags = { "GET" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Éxito", content = @Content(schema = @Schema(implementation = ProfesorDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/")
    public ResponseEntity<ProfesorDto> getProfesorById(@RequestParam(name = "ProfesorId") Long ProfesorId) {
        ProfesorDto ProfesorDto = this.ProfesorService.getProfesorById(ProfesorId);
        return new ResponseEntity<>(ProfesorDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Crear un nuevo Profesor",
            description = "Crea un nuevo Profesor y devuelve los detalles del Profesor creado.",
            tags = { "POST" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Creado", content = @Content(schema = @Schema(implementation = ProfesorDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "412", description = "Error de formato en la solicitud"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/create")
    public ResponseEntity<ProfesorDto> createProfesor(@RequestBody ProfesorDto ProfesorDto) {
        ProfesorDto createdProfesor = this.ProfesorService.createProfesor(ProfesorDto);
        return new ResponseEntity<>(createdProfesor, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Desactivar un Profesor",
            description = "Desactiva un Profesor estableciendo su estado como inactivo.",
            tags = { "DELETE" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profesor desactivado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteProfesor(@PathVariable Long id) {
        Map<String, Object> mapResponse = this.ProfesorService.deleteProfesor(id);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Actualizar un Profesor",
            description = "Actualiza los detalles de un Profesor existente.",
            tags = { "PUT" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actualizado exitosamente", content = @Content(schema = @Schema(implementation = ProfesorDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/update")
    public ResponseEntity<Optional<ProfesorDto>> updateProfesor(@RequestBody ProfesorDto ProfesorDto) {
        Optional<ProfesorDto> updatedProfesor = this.ProfesorService.updateProfesor(ProfesorDto);
        return new ResponseEntity<>(updatedProfesor, HttpStatus.OK);
    }

}
