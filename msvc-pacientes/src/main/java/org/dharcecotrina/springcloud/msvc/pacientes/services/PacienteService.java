package org.dharcecotrina.springcloud.msvc.pacientes.services;

import org.dharcecotrina.springcloud.msvc.pacientes.models.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    List<Paciente> listar(); //lista  user
    Optional<Paciente> porId(Long id); //te devuelve un solo valor
    Paciente guardar(Paciente usuario); //para gaurdar los datos o un user
    void eliminar(Long id);

    Optional<Paciente> porSeguro(String email);

    //Metodos remotos

    List<Paciente> listaPorIds(Iterable<Long> ids);
}
