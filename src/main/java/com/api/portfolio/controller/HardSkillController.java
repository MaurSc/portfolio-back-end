package com.api.portfolio.controller;

import com.api.portfolio.dto.Mensaje;
import com.api.portfolio.dto.NewHardSkillCateg;
import com.api.portfolio.enums.CatNombre;
import com.api.portfolio.model.CategoriaSkill;
import com.api.portfolio.model.HardSkill;
import com.api.portfolio.model.Person;
import com.api.portfolio.service.ICategoriaSkillService;
import com.api.portfolio.service.IHardService;
import com.api.portfolio.service.IPersonService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class HardSkillController {
    
    @Autowired
    private IHardService hardService;
    
    @Autowired
    private ICategoriaSkillService catService;
    
    @Autowired
    private IPersonService personService;
    
    @GetMapping("/hard")
    public ResponseEntity<List<HardSkill>> getHardSkills(){
        List<HardSkill> list = hardService.getHSkills();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/hard")
    public ResponseEntity<?> createHardSkill(@Valid @RequestBody NewHardSkillCateg hardSkill, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Se debe introducir un nombre"), HttpStatus.BAD_REQUEST);
        HardSkill hSkill = 
                new HardSkill(hardSkill.getName());
        List<Person> person;
        person = personService.getPerson();
        for (Person p: person){
            hSkill.setPerson(p);
        }
        Set<CategoriaSkill> categors = new HashSet<>();
        String s = hardSkill.getCategories().toLowerCase();
        if(null != s)
            switch (s) {
            case "frontend":
                categors.add((CategoriaSkill)catService.findByCatNombre(CatNombre.FRONT_END).get());
                break;
            case "backend":
                categors.add((CategoriaSkill)catService.findByCatNombre(CatNombre.BACK_END).get());
                break;
            case "other":
                categors.add((CategoriaSkill)catService.findByCatNombre(CatNombre.OTHER).get());
                break;
            default:
                break;
        }
        hSkill.setCatSkills(categors);
        hardService.saveHSkills(hSkill);
        return new ResponseEntity(new Mensaje("Elemento creado exitosamente"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/hard/{id}")
    public ResponseEntity<?> deleteHardSkill(@PathVariable Integer id){
        if(!hardService.isSkill(id))
            return new ResponseEntity(new Mensaje("no se pudo encontrar el recurso"), HttpStatus.NOT_FOUND);
        hardService.deleteHSkill(id);
        return new ResponseEntity(new Mensaje("recurso elimnado con exitos"), HttpStatus.OK);
    }
}
