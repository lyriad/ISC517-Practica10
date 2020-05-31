package com.web.pucmm.practica10.Models;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String permission;

    public Permission() {

    }

    public Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}