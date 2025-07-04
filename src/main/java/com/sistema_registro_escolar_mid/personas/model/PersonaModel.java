package com.sistema_registro_escolar_mid.personas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "persona", schema = "ares-sre")
public class PersonaModel {

    @Id
    @Column(name = "pe_id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pe_nombre")
    private String names;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pe_apellido")
    private String lastNames;

    @NotNull
    @Column(name = "pe_fecha_nacimiento")
    private Date birthDate;

    @Email
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pe_email", unique=true)
    private String email;

    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "pe_telefono")
    private String phone;

}