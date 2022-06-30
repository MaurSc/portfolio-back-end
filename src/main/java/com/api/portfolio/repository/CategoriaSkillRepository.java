package com.api.portfolio.repository;

import com.api.portfolio.enums.CatNombre;
import com.api.portfolio.model.CategoriaSkill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaSkillRepository extends JpaRepository<CategoriaSkill, Integer>{
    Optional<CategoriaSkill> findByCatNombre(CatNombre catNombre);
}
