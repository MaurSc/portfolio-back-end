package com.api.portfolio.service;

import com.api.portfolio.enums.CatNombre;
import com.api.portfolio.model.CategoriaSkill;
import java.util.Optional;


public interface ICategoriaSkillService {
    
    public Optional<CategoriaSkill> findByCatNombre(CatNombre catNombre);
    
    public void save(CategoriaSkill catSkill);

}
