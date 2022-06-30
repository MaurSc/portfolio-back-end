package com.api.portfolio.security.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;

public class NuevoUsuario {
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;
    
    private Set<String> roles = new HashSet<>();

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
