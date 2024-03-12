package org.dharcecotrina.springcloud.msvc.pacientes.repositories;

import org.dharcecotrina.springcloud.msvc.pacientes.models.entity.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PacienteRepository extends CrudRepository<Paciente,Long> {

    Optional<Paciente> findBySeguro(String seguro);
}
