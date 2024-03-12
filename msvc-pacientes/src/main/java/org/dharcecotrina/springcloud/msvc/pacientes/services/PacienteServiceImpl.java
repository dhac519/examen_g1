package org.dharcecotrina.springcloud.msvc.pacientes.services;

import org.dharcecotrina.springcloud.msvc.pacientes.models.entity.Paciente;
import org.dharcecotrina.springcloud.msvc.pacientes.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class PacienteServiceImpl implements  PacienteService{


    @Autowired //para inyectar la dependencia de una clase con metodos
    private PacienteRepository repository;

//    @Autowired
//    private CursoClienteRest cursoClienteRest;

    @Override
    @Transactional(readOnly = true) //springframework.transaction.annotation - solo de lectura
    public List<Paciente> listar() {
        return (List<Paciente>) repository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Paciente> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
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
    public Optional<Paciente> porSeguro(String seguro) {
        return repository.findBySeguro(seguro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> listaPorIds(Iterable<Long> ids) {
        return (List<Paciente>) repository.findAllById(ids);
    }


}
