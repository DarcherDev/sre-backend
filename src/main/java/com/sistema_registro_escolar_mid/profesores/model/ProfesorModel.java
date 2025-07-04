package com.sistema_registro_escolar_mid.profesores.model;

import com.sistema_registro_escolar_mid.personas.model.PersonaModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profesor", schema = "ares-sre")
public class ProfesorModel {

    @Id
    @Column(name = "pr_id_profesor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "pr_especialidad", unique=true)
    private String especialidad;

    @NotNull
    @Column(name = "pr_fecha_contratacion")
    private Date contratacionFecha;

    @ManyToOne
    @JoinColumn(name = "pe_id_persona", nullable = false)
    private PersonaModel persona;
}