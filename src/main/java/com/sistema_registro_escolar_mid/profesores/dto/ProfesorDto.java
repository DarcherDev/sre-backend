package com.sistema_registro_escolar_mid.profesores.dto;

import com.sistema_registro_escolar_mid.personas.dto.PersonaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDto {

    @Schema(description = "Identificador del estudiante", example = "1")
    private Long id;

    @Schema(description = "especialidad del profesor", example = "matematicas")
    @NotBlank(message = "especialidad del profesor es obligatorio")
    private String especialidad;

    @Schema(description = "fecha de contratacion del profesor", example = "2015-05-20")
    @NotBlank(message = "fecha de contratacion obligatorio")
    private String contratacionFecha;

    @Schema(description = "", example = "")
    @NotBlank(message = "")
    private PersonaDto persona;

}
