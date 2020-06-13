package com.web.pucmm.practica10.Models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "rentals")
public class Rental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long id;

    @OneToOne
    private Equipment equipment;

    @ManyToOne
    private User client;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date promisedReturnDate;

    @Column
    private Date realReturnDate;

    public Rental() {

    }

    public Rental( Equipment equipment, User client, int amount, Date createdAt, Date promisedReturnDate ) {
        this.equipment = equipment;
        this.client = client;
        this.amount = amount;
        this.createdAt = createdAt;
        this.promisedReturnDate = promisedReturnDate;
        this.realReturnDate = null;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public User getClient() {
        return this.client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPromisedReturnDate() {
        return this.promisedReturnDate;
    }

    public void setPromisedReturnDate(Date promisedReturnDate) {
        this.promisedReturnDate = promisedReturnDate;
    }

    public Date getRealReturnDate() {
        return this.realReturnDate;
    }

    public void setRealReturnDate(Date realReturnDate) {
        this.realReturnDate = realReturnDate;
    }

    public int getRentedDays() {
        Date now = new Date();
        long diff = now.getTime() - this.createdAt.getTime();
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));

        return diffDays;
    }
    
    public double getCost() {
        return this.getRentedDays() * this.getEquipment().getCostPerDay() * this.getAmount();
    }
}