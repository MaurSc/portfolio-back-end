package com.api.portfolio.service;

import com.api.portfolio.model.Education;
import java.util.List;

public interface IEducationService {
    
    public List<Education> getEducation();
    
    public void saveEducation(Education education);
    
    public void deleteEducation(Integer id);
    
    public boolean isEdu(Integer id);
    
    public void updateEducation(Integer id, String sName, String description, String certificate, String dateA, String dateD);
}
