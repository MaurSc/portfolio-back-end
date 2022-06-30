package com.api.portfolio.service;

import com.api.portfolio.model.HardSkill;
import com.api.portfolio.repository.HardSkillRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HardService implements IHardService{

    @Autowired
    private HardSkillRepository repo;
    @Override
    public List<HardSkill> getHSkills() {
        List<HardSkill> listaHS = repo.findAll();
        return listaHS;
    }

    @Override
    public void saveHSkills(HardSkill hardS) {
        repo.save(hardS);
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
