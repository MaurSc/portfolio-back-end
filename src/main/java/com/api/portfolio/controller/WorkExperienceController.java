package com.api.portfolio.controller;

import com.api.portfolio.dto.Mensaje;
import com.api.portfolio.model.Person;
import com.api.portfolio.model.WorkExperience;
import com.api.portfolio.service.IPersonService;
import com.api.portfolio.service.IWorkExpService;
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
@RequestMapping("/work")
public class WorkExperienceController {
    
    @Autowired
    private IWorkExpService service;
    
    @Autowired
    private IPersonService personService;
    
    @GetMapping("/")
    public ResponseEntity<List<WorkExperience>> getWorks(){
        List<WorkExperience> list = service.getWorks();
        return new ResponseEntity(list, HttpStatus.OK);
        
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> createWork(@Valid @RequestBody WorkExperience work, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("LLenar los campos obligatorios"), HttpStatus.BAD_REQUEST);
        WorkExperience workExperience = 
                new WorkExperience( work.getCompany(), work.getDateA(), work.getDateD(), work.getDescription());
        List<Person> person = personService.getPerson();
        for(Person p : person){
            workExperience.setPerson(p);
        }
        service.createWork(workExperience);
        return new ResponseEntity(new Mensaje("Recurso creado correctament"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateWork(@PathVariable Integer id,
                             @RequestParam String company,
                             @RequestParam String description,
                             @RequestParam String dateA,
                             @RequestParam String dateD){
        if(!service.isWork(id))
            return new ResponseEntity(new Mensaje("El recurso no existe"), HttpStatus.NOT_FOUND);
        service.updateWork(id, company, description, dateA, dateD);
        return new ResponseEntity(new Mensaje("Recurso actualizado correctamente"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWork(@PathVariable Integer id){
        if(!service.isWork(id))
            return new ResponseEntity(new Mensaje("El recurso no existe"), HttpStatus.NOT_FOUND);
        service.deleteWork(id);
        return new ResponseEntity(new Mensaje("El recurso fue elimnado correctamente"), HttpStatus.OK);
    }
}
