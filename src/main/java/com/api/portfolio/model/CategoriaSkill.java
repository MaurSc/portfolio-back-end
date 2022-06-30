package com.api.portfolio.model;

import com.api.portfolio.enums.CatNombre;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category_skill")
public class CategoriaSkill implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
        private Integer id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "catName", nullable = false)
        private CatNombre catNombre;

    public CategoriaSkill() {
    }

    public CategoriaSkill(@NotNull CatNombre catName) {
        this.catNombre = catName;
    }
    
}