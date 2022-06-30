package com.api.portfolio.controller;

import com.api.portfolio.dto.Mensaje;
import com.api.portfolio.model.Person;
import com.api.portfolio.model.SoftSkill;
import com.api.portfolio.service.IPersonService;
import com.api.portfolio.service.ISoftService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
public class SoftSkillController {
    
    @Autowired
    private ISoftService service;
    
    @Autowired
    private IPersonService personService;
    
    @GetMapping("/soft")
    public ResponseEntity<List<SoftSkill>> getSoftSkills(){
        List<SoftSkill> list = service.getSkill();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/soft")
    public ResponseEntity<?> createSoftSkill(@Valid @RequestBody SoftSkill sSkill, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Falta nombrar la Skill"), HttpStatus.BAD_REQUEST);
        SoftSkill softSkill = 
                new SoftSkill(sSkill.getName());
        List<Person> person = personService.getPerson();
        for(Person p : person){
            softSkill.setPerson(p);
        }
        service.saveHSkills(softSkill);
        return new ResponseEntity(new Mensaje("Recurso guardado correctamente"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<?> deleteSoftSkill (@PathVariable Integer id){
        if(!service.isSkill(id))
            return new ResponseEntity(new Mensaje("El recurso no existe"), HttpStatus.NOT_FOUND);
        service.deleteHSkill(id);
        return new ResponseEntity(new Mensaje("El recurso fue eliminado exitosamente"), HttpStatus.OK);   
    }
}
