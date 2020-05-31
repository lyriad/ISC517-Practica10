package com.web.pucmm.practica10.Models;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String role;

    @OneToMany
    private Collection<Permission> permissions;

    public Role() {
    }

    public Role(String role, Collection<Permission> permissions) {
        this.role = role;
        this.permissions = permissions;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions = permissions;
    }

}