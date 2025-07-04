package com.sistema_registro_escolar_mid.personas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {

    @Schema(description = "Identificador del persona", example = "1")
    private Long id;

    @Schema(description = "Nombre del persona", example = "Juan")
    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @Schema(description = "Apellidos del persona", example = "Pérez Gómez")
    @NotBlank(message = "El apellido es obligatorio")
    private String lastNames;

    @Schema(description = "Fecha de nacimiento del persona", example = "1990-05-20")
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    private String birthDate;

    @Schema(description = "Correo electrónico del persona", example = "persona@correo.com")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Schema(description = "Teléfono del persona", example = "3001234567")
    private String phone;

}
