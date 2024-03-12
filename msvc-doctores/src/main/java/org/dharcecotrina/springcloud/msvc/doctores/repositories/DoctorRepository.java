package org.dharcecotrina.springcloud.msvc.doctores.repositories;

import org.dharcecotrina.springcloud.msvc.doctores.models.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor,Long> {
    Optional<Doctor> findByColegiatura(Integer colegiatura);
}
