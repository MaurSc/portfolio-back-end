package com.api.portfolio.service;

import com.api.portfolio.model.SoftSkill;
import java.util.List;

public interface ISoftService {
    
    public List<SoftSkill> getSkill();
    
    public void saveHSkills(SoftSkill softS);
    
    public boolean isSkill(Integer id);
    
    public void deleteHSkill(Integer id);
}
