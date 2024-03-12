package org.dharcecotrina.springcloud.msvc.doctores.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "doctores")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDoctor;
    @NotBlank
    @NotEmpty
    private  String nombres;
    @NotNull
    @Pattern(regexp = "\\d{6}")
    private  Integer colegiatura;

    public long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(long idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(Integer colegiatura) {
        this.colegiatura = colegiatura;
    }
}
