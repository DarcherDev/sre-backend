package com.sistema_registro_escolar_mid.estudiantes.dto;

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
public class EstudianteDto {

    @Schema(description = "Identificador del estudiante", example = "1")
    private Long id;

    @Schema(description = "numero de matricula del estudiante", example = "1860532")
    @NotBlank(message = "numero de matricula es obligatorio")
    private String numeroMatricula;

    @Schema(description = "grado del estudiante", example = "decimo")
    @NotBlank(message = "grado del estudiante es obligatorio")
    private String grado;

    @Schema(description = "", example = "")
    @NotBlank(message = "")
    private PersonaDto persona;

}
