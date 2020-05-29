package com.web.pucmm.practica10.Models;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    protected long id;

    @Column(nullable = false, unique = true)
    protected String idNumber;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String lastName;

    @Column(nullable = false)
    protected String phone;

    @Column(nullable = false)
    protected String address;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    protected byte[] image;

    public Person() {

    }

    public Person(String idNumber, String name, String lastName, String phone, String address, byte[] image) {
        this.idNumber = idNumber;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.image = image;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}