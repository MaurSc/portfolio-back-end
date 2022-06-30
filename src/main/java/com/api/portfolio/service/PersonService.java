package com.api.portfolio.service;

import com.api.portfolio.model.Person;
import com.api.portfolio.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService{

    @Autowired
    private PersonRepository repo;
    
    @Override
    public List<Person> getPerson() {
        List<Person> person = repo.findAll();
        return person;
    }
    @Override
    public void createPerson(Person person) {
        repo.save(person);
    }
    @Override
    public void updatePerson(Integer id, String name, String lName, String adress) {
        Person per = repo.findById(id).orElse(null);
        per.setName(name);
        per.setlName(lName);
        per.setAdress(adress);
        
        repo.save(per);
    }

    @Override
    public boolean isPerson(Integer id) {
        return repo.existsById(id);
    }
}
