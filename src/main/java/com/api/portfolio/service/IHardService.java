package com.api.portfolio.service;

import com.api.portfolio.model.HardSkill;
import java.util.List;

public interface IHardService {
    
    public List<HardSkill> getHSkills();
    
    public void saveHSkills(HardSkill hardS);
    
     public boolean isSkill(Integer id);
    
    public void deleteHSkill(Integer id);
}
