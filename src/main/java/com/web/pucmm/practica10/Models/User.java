package com.web.pucmm.practica10.Models;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "users")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String idNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    @OneToMany
    private Collection<Role> roles;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    public User() {

    }

    public User(String idNumber, String name, String email, String lastName, String phone, String address, String password, Collection<Role> roles, byte[] image) {
        this.idNumber = idNumber;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.roles = roles;
        this.image = image;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}