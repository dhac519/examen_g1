package org.dharcecotrina.springcloud.msvc.pacientes.controllers;

import jakarta.validation.Valid;
import org.dharcecotrina.springcloud.msvc.pacientes.models.entity.Paciente;
import org.dharcecotrina.springcloud.msvc.pacientes.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/paciente") //
@RestController
public class PacienteController {
    @Autowired
    private PacienteService service;


    @GetMapping
    public List<Paciente> listar(){
        return service.listar();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){ //Enviar Valores primitivos
        Optional<Paciente> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crea(@Valid @RequestBody Paciente paciente, BindingResult result){ //Enviar Objetos


        if(service.porSeguro(paciente.getSeguro()).isPresent()){
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
    public ResponseEntity<?> editar(@Valid @RequestBody Paciente paciente, BindingResult result, @PathVariable Long id){


        if(result.hasErrors()){
            return  validar(result);
        }

        Optional<Paciente> op = service.porId(id);
        if(op.isPresent()){
            Paciente pacienteDB = op.get();
            if (!paciente.getSeguro().equalsIgnoreCase(pacienteDB.getSeguro()) && service.porSeguro(paciente.getSeguro()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Arce Cotrina","Ya existe el email en la data"));
            }
            pacienteDB.setNombres(paciente.getNombres());
            pacienteDB.setSeguro(paciente.getSeguro());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(pacienteDB));//:)
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
        Optional<Paciente> op = service.porId(id);
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
