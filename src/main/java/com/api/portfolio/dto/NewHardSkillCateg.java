package com.api.portfolio.dto;

import javax.validation.constraints.NotBlank;

public class NewHardSkillCateg {
    @NotBlank
    private String name;
    
    @NotBlank
    private String category;

    public NewHardSkillCateg(String name, String category) {
        this.name = name;
        this.category = category;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return category;
    }

    public void setCategories(String categories) {
        this.category = categories;
    }
}
