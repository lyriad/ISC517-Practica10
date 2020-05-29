package com.web.pucmm.practica10.Models;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long idClient;

    public Client() {
    }

    public Client(String idNumber, String name, String lastName, String phone, String address, byte[] image) {
        this.idNumber = idNumber;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.image = image;
    }

    public long getIdClient() {
        return this.idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }
}