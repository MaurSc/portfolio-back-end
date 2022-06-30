package com.api.portfolio.service;

import com.api.portfolio.enums.CatNombre;
import com.api.portfolio.model.CategoriaSkill;
import com.api.portfolio.repository.CategoriaSkillRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriaSkillService implements ICategoriaSkillService{

    @Autowired
    private CategoriaSkillRepository repo;

    @Override
    public Optional<CategoriaSkill> findByCatNombre(CatNombre catNombre) {
        return repo.findByCatNombre(catNombre);
    }

    @Override
    public void save(CategoriaSkill catSkill) {
        repo.save(catSkill);
    }
}
