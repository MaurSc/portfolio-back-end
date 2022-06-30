package com.api.portfolio.service;

import com.api.portfolio.model.Education;
import com.api.portfolio.repository.EducationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService implements IEducationService{

    @Autowired
    private EducationRepository repo;
    
    @Override
    public List<Education> getEducation() {
        List<Education> listEducation =  repo.findAll();
        return listEducation;
    }

    @Override
    public void saveEducation(Education education) {
        repo.save(education);
    }

    @Override
    public void deleteEducation(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public boolean isEdu(Integer id) {
       return repo.existsById(id);
    }
    @Override
    public void updateEducation(Integer id, String sName, String description, String certificate, String dateA, String dateD) {
        Education edu = repo.findById(id).orElse(null);
        edu.setInsName(sName);
        edu.setDateA(dateA);
        edu.setDateD(dateD);
        edu.setDescription(description);
        edu.setCertificate(certificate);
        repo.save(edu);
    }
    
}
