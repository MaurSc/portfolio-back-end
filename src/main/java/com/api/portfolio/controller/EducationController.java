package com.api.portfolio.controller;

import com.api.portfolio.dto.Mensaje;
import com.api.portfolio.model.Education;
import com.api.portfolio.model.Person;
import com.api.portfolio.service.IEducationService;
import com.api.portfolio.service.IPersonService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/education")
public class EducationController {
    
    @Autowired
    private IEducationService service;
    
    @Autowired
    private IPersonService personService;
    
    @GetMapping("/")
    public ResponseEntity<List<Education>> getEducation(){
        List<Education> list = service.getEducation();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> saveEducation(@Valid @RequestBody Education edu, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("elementos ingresados incorrectamente"), HttpStatus.BAD_REQUEST);
        Education education = 
                new Education(edu.getInsName(),
                              edu.getDateA(),
                              edu.getDateD(),
                              edu.getDescription(),
                              edu.getCertificate());
        List<Person> person = personService.getPerson();
        for(Person p: person){
            education.setPerson(p);
        }
        service.saveEducation(education);
        return new ResponseEntity(new Mensaje("carga correcta"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable Integer id){
        if(!service.isEdu(id))
            return new ResponseEntity(new Mensaje("El recurso no se puede eliminar porque no existe"), HttpStatus.NOT_FOUND);
        service.deleteEducation(id);
        return new ResponseEntity(new Mensaje("El recurso se elimno con exito"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEducation(@PathVariable Integer id,
                                  @RequestParam String sName,
                                  @RequestParam String description,
                                  @RequestParam  String certificate,
                                  @RequestParam String dateA,
                                  @RequestParam String dateD){
        if(!service.isEdu(id))
            return new ResponseEntity(new Mensaje("El recurso no se puede editar porque no existe"), HttpStatus.NOT_FOUND);
        
        service.updateEducation(id, sName, description, certificate, dateA, dateD);
        return new ResponseEntity(new Mensaje("Recurso actualizado"), HttpStatus.OK);
    }
}
