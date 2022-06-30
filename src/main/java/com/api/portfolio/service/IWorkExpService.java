package com.api.portfolio.service;

import com.api.portfolio.model.WorkExperience;
import java.util.List;

public interface IWorkExpService {
    
    public List<WorkExperience> getWorks();
    
    public void createWork(WorkExperience work);

    public boolean isWork(Integer id);
    
    public void updateWork(Integer id, String company, String description, String dateA, String dateD);
    
    public void deleteWork(Integer id);
}
