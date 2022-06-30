package com.api.portfolio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Person  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
        private Integer id;
    @Column(name = "person_name", nullable = false, length = 20)
        private String name;
    @Column(name = "person_lName", nullable = false, length = 20)  
        private String lName;
    @Column(name = "person_adress", nullable = false, length = 20)
        private String adress;

    
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HardSkill> hSkills = new ArrayList<>();
    
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Education> education = new ArrayList<>();
    
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projects = new ArrayList<>();
    
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SoftSkill> sSkills = new ArrayList<>();
    
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<WorkExperience> experiences = new ArrayList<>();
    
    
    public Person() {
    }

    public Person(String name, String lName, String adress) {
        this.name = name;
        this.lName = lName;
        this.adress = adress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<HardSkill> gethSkills() {
        return hSkills;
    }

    public void sethSkills(List<HardSkill> hSkills) {
        this.hSkills = hSkills;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<SoftSkill> getsSkills() {
        return sSkills;
    }

    public void setsSkills(List<SoftSkill> sSkills) {
        this.sSkills = sSkills;
    }

    public List<WorkExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<WorkExperience> experiences) {
        this.experiences = experiences;
    }
    
    
}
