package org.dharcecotrina.springcloud.msvc.doctores.controllers;

import jakarta.validation.Valid;
import org.dharcecotrina.springcloud.msvc.doctores.models.entity.Doctor;
import org.dharcecotrina.springcloud.msvc.doctores.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RequestMapping("/api/doctor") //
@RestController
public class DoctorController {
    @Autowired
    private DoctorService service;


    @GetMapping
    public List<Doctor> listar(){
        return service.listar();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){ //Enviar Valores primitivos
        Optional<Doctor> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crea(@Valid @RequestBody Doctor paciente, BindingResult result){ //Enviar Objetos


        if(service.porColegiatura(paciente.getColegiatura()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Arce Cotrina","Ya existe un usuario con ese e-mail"));
        }
        if(result.hasErrors()){
            Map<String, String> errores=new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "Arce Cotrina " + err.getField() +
                        " " + err.getDefaultMessage());
            });
            return    ResponseEntity.badRequest().body(errores);

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(paciente));

    }



    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Doctor doctor, BindingResult result, @PathVariable Long id){


        if(result.hasErrors()){
            return  validar(result);
        }

        Optional<Doctor> op = service.porId(id);
        if(op.isPresent()){
            Doctor doctorDB = op.get();
            if (doctor.getColegiatura() != doctorDB.getColegiatura() && service.porColegiatura(doctor.getColegiatura()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Arce Cotrina","Ya existe el email en la data"));
            }
            doctorDB.setNombres(doctor.getNombres());
            doctorDB.setColegiatura(doctor.getColegiatura());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(doctorDB));//:)
        }

        return ResponseEntity.notFound().build();
    }
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores=new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Arce Cotrina " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Doctor> op = service.porId(id);
        if(op.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids){
        return  ResponseEntity.ok(service.listaPorIds(ids));

    }
}
