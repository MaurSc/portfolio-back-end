package com.api.portfolio.service;

import com.api.portfolio.model.WorkExperience;
import com.api.portfolio.repository.WorkExpRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkService implements IWorkExpService{
    
    @Autowired
    private WorkExpRepository repo;

    @Override
    public List<WorkExperience> getWorks() {
        List<WorkExperience> listWorks = repo.findAll();
        return listWorks;
    }

    @Override
    public void createWork(WorkExperience work) {
        repo.save(work);
    }

    @Override
    public void updateWork(Integer id, String company, String description, String dateA, String dateD) {
        WorkExperience work = repo.findById(id).orElse(null);
        work.setCompany(company);
        work.setDescription(description);
        work.setDateA(dateA);
        work.setDateD(dateD);
        
        repo.save(work);
    }

    @Override
    public void deleteWork(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public boolean isWork(Integer id) {
        return repo.existsById(id);
    }
}
