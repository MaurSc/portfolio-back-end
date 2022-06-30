package com.api.portfolio.controller;

import com.api.portfolio.dto.Mensaje;
import com.api.portfolio.model.Person;
import com.api.portfolio.service.IPersonService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    private IPersonService service;
    
    @GetMapping("/")
    public ResponseEntity<List<Person>> getPerson(){
        List<Person> list = service.getPerson();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Has introducido un campo de manera erronea"),HttpStatus.BAD_REQUEST);
        service.createPerson(person);
        return new ResponseEntity(new Mensaje("Recurso creado correctamente"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Integer id,
                               @RequestParam String name,
                               @RequestParam String lName,
                               @RequestParam String adress){
        if(!service.isPerson(id))
            return new ResponseEntity(new Mensaje("El recurso no existe"), HttpStatus.NOT_FOUND);
        service.updatePerson(id, name, lName, adress);
        return new ResponseEntity(new Mensaje("El recurso se actualizo correctamente"), HttpStatus.OK);
    }
}
