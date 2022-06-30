package com.api.portfolio.service;

import com.api.portfolio.model.Person;
import java.util.List;

public interface IPersonService {
    
    public List<Person> getPerson();
    
    public void createPerson(Person person);
    
    public boolean isPerson(Integer id);
    
    public void updatePerson(Integer id, String name, String lName, String adress);
}
