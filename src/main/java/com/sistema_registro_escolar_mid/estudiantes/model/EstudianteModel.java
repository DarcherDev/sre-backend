package com.sistema_registro_escolar_mid.estudiantes.model;

import com.sistema_registro_escolar_mid.personas.dto.PersonaDto;
import com.sistema_registro_escolar_mid.personas.model.PersonaModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estudiante", schema = "ares-sre")
public class EstudianteModel {

    @Id
    @Column(name = "es_id_estudiante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "es_numero_matricula", unique=true)
    private String numeroMatricula;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "es_grado")
    private String grado;

    @ManyToOne
    @JoinColumn(name = "es_id_persona", nullable = false)
    private PersonaModel persona;
}