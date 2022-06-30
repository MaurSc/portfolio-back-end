package com.api.portfolio.service;

import com.api.portfolio.model.SoftSkill;
import com.api.portfolio.repository.SoftSkillRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoftService implements ISoftService{
    
    @Autowired
    private SoftSkillRepository repo;

    @Override
    public List<SoftSkill> getSkill() {
        List<SoftSkill> listSSkill = repo.findAll();
        return listSSkill;
    }

    @Override
    public void saveHSkills(SoftSkill softS) {
        repo.save(softS);
    }

    @Override
    public void deleteHSkill(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public boolean isSkill(Integer id) {
        return repo.existsById(id);
    }
    
}
