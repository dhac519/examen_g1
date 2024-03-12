package org.dharcecotrina.springcloud.msvc.doctores.services;

import org.dharcecotrina.springcloud.msvc.doctores.models.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    List<Doctor> listar(); //lista  user
    Optional<Doctor> porId(Long id); //te devuelve un solo valor
    Doctor guardar(Doctor doctor); //para gaurdar los datos o un user
    void eliminar(Long id);

    Optional<Doctor> porColegiatura(Integer colegiatura);

    //Metodos remotos

    List<Doctor> listaPorIds(Iterable<Long> ids);
}
