package com.api.portfolio.service;

import com.api.portfolio.model.Project;
import java.util.List;

public interface IProjectService {
    
    public List<Project> getProject();
    
    public void createProject(Project project);
    
    public boolean isProject(Integer id); 
    
    public void deleteProject(Integer id);
    
    public void updateProject(Integer id, String name, String image, String repositorio);
    
}
