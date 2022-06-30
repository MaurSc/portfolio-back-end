package com.api.portfolio.service;

import com.api.portfolio.model.Project;
import com.api.portfolio.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService{

    @Autowired
    private ProjectRepository repo;
    @Override
    public List<Project> getProject() {
        List<Project> listaProjects = repo.findAll();
        return listaProjects;
    }
    @Override
    public void createProject(Project project) {
        repo.save(project);
    }

    @Override
    public void deleteProject(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public void updateProject(Integer id, String name, String image, String repositorio) {
        Project pro = repo.findById(id).orElse(null);
        pro.setName(name);
        pro.setImage(image);
        pro.setRepositorio(repositorio);
        
        repo.save(pro);
    }

    @Override
    public boolean isProject(Integer id) {
        return repo.existsById(id);
    }
}
