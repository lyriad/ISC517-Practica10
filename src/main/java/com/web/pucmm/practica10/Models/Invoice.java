package com.web.pucmm.practica10.Models;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @OneToMany
    private Collection<Rental> rentals;

    @OneToOne
    private User client;

    @OneToOne
    private User employee;

    private float total = 0;

    private boolean paid = false;

    public Invoice() {
    }

    public Invoice(Collection<Rental> rentals, User client, User employee, float total, boolean paid) {
        this.rentals = rentals;
        this.client = client;
        this.employee = employee;
        this.total = total;
        this.paid = paid;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<Rental> getRentals() {
        return this.rentals;
    }

    public void setRentals(Collection<Rental> rentals) {
        this.rentals = rentals;
    }

    public User getClient() {
        return this.client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getEmployee() {
        return this.employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isPaid() {
        return this.paid;
    }

    public boolean getPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}