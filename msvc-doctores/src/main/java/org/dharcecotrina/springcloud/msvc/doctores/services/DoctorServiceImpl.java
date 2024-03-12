package org.dharcecotrina.springcloud.msvc.doctores.services;

import org.dharcecotrina.springcloud.msvc.doctores.models.entity.Doctor;
import org.dharcecotrina.springcloud.msvc.doctores.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired //para inyectar la dependencia de una clase con metodos
    private DoctorRepository repository;

//    @Autowired
//    private CursoClienteRest cursoClienteRest;

    @Override
    @Transactional(readOnly = true) //springframework.transaction.annotation - solo de lectura
    public List<Doctor> listar() {
        return (List<Doctor>) repository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Doctor> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Doctor guardar(Doctor doctor) {
        return repository.save(doctor);
    }

    @Override
    @Transactional
//    public void eliminar(Long id) {
//        repository.deleteById(id);
//        cursoClienteRest.eliminarCursoUsuarioPorId(id);
//    }
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Doctor> porColegiatura(Integer colegiatura) {
        return repository.findByColegiatura(colegiatura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctor> listaPorIds(Iterable<Long> ids) {
        return (List<Doctor>) repository.findAllById(ids);
    }


}
