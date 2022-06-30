package com.api.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "education")
public class Education implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
        private Integer id;
    @Column(name = "edu_name", nullable = false, length = 20)
        private String insName;
    @Column(name = "edu_dateA", nullable = false, length = 20)
        private String dateA = "Actualidad";
    @Column(name = "edu_dateD", nullable = false, length = 20)
        private String dateD;
    @Column(name = "edu_description", nullable = false, length = 250)
        private String description;
    @Column(name = "edu_certificate", nullable = false, length = 150)
        private String certificate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id",referencedColumnName="id")
        private Person person;

    public Education(String insName, String dateA, String dateD, String description, String certificate) {
        this.insName = insName;
        this.dateA = dateA;
        this.dateD = dateD;
        this.description = description;
        this.certificate = certificate;
    }

    public Education() {
    }
        
    
}
