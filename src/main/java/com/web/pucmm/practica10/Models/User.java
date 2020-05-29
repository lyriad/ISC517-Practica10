package com.web.pucmm.practica10.Models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long idUser;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String idNumber, String name, String lastName, String phone, String address, byte[] image, String password) {
        this.idNumber = idNumber;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.image = image;
        this.password = password;
    }

    public long getIdUser() {
        return this.idUser;
    }

    public void setId_user(long idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}