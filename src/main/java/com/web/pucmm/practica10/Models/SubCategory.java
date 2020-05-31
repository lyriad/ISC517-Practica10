package com.web.pucmm.practica10.Models;

import javax.persistence.*;

@Entity
@Table(name = "sub_categories")
public class SubCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Category category;

    public SubCategory() {

    }

    public SubCategory(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}