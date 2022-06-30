package com.api.portfolio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hard_skill")
public class HardSkill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hard_id")
        private Integer id;
    @Column(name = "hard_name", nullable = false, length = 20)
        private String name;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id",referencedColumnName="id")
        private Person person;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "hardSkill_categories", 
      joinColumns = @JoinColumn(name = "hardSkill_id"), 
      inverseJoinColumns = @JoinColumn(name = "categories_id"))
        private Set<CategoriaSkill> catSkills = new HashSet<>();

    public HardSkill() {
    }

    public HardSkill(String name) {
        this.name = name;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<CategoriaSkill> getCatSkills() {
        return catSkills;
    }

    public void setCatSkills(Set<CategoriaSkill> CatSkills) {
        this.catSkills = CatSkills;
    }
}
