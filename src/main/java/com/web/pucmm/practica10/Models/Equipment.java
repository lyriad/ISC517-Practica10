package com.web.pucmm.practica10.Models;

import javax.persistence.*;

import org.json.simple.JSONObject;

@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    private Category category;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private int cantAvailable;

    @Column(nullable = false)
    private float costPerDay;

    @Column
    private String image;

    public Equipment () {

    }

    public Equipment( String name, SubCategory subCategory, Category category, int cantAvailable, float costPerDay, String image ) {
        this.name = name;
        this.subCategory = subCategory;
        this.category = category;
        this.cantAvailable = cantAvailable;
        this.costPerDay = costPerDay;
        this.image = image;
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

    public SubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCantAvailable() {
        return this.cantAvailable;
    }

    public void setCantAvailable(int cantAvailable) {
        this.cantAvailable = cantAvailable;
    }

    public float getCostPerDay() {
        return this.costPerDay;
    }

    public void setCostPerDay(float costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toJson() {

        JSONObject json = new JSONObject();

        json.put("id", this.id);
        json.put("name", this.name);
        json.put("category", this.category);
        json.put("subCategory", this.subCategory);
        json.put("cantAvailable", this.cantAvailable);
        json.put("costPerDay", this.costPerDay);
        json.put("image", this.image);

        return json.toJSONString();
    }

}