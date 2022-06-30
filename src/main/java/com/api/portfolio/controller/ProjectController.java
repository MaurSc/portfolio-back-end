package com.api.portfolio.controller;

import com.api.portfolio.dto.Mensaje;
import com.api.portfolio.model.Person;
import com.api.portfolio.model.Project;
import com.api.portfolio.service.IPersonService;
import com.api.portfolio.service.IProjectService;
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
@RequestMapping("/project")
public class ProjectController {
    
    @Autowired
    private IProjectService service;
    
    @Autowired
    private IPersonService personService;
    
    @GetMapping("/")
    public ResponseEntity<List<Project>> getProjects(){
        List<Project> list = service.getProject();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project pro, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("error en los campos"), HttpStatus.BAD_REQUEST);
        Project project = 
                new Project(pro.getName(), pro.getImage(), pro.getRepositorio());
        List<Person> person = personService.getPerson();
        for(Person p: person){
            project.setPerson(p);
        }
        service.createProject(project);
        return new ResponseEntity(new Mensaje("Projecto creado con exito"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id){
        if(!service.isProject(id))
            return new ResponseEntity(new Mensaje("El recurso no se pudo eliminar porque no existe"), HttpStatus.NOT_FOUND);
        service.deleteProject(id);
        return new ResponseEntity(new Mensaje("Recurso eliminado de manera exitosa"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Integer id,
                                @RequestParam String name,
                                @RequestParam String image,
                                @RequestParam String repositorio){
        if(!service.isProject(id))
            return new ResponseEntity(new Mensaje("El recurso no se pudo eliminar porque no existe"), HttpStatus.NOT_FOUND);
        service.updateProject(id, name, image, repositorio);
        return new ResponseEntity(new Mensaje("Recurso actualizado correctamente"), HttpStatus.OK);
    }
}
