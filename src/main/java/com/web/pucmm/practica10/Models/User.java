package com.web.pucmm.practica10.Models;

import java.util.Set;
import javax.persistence.*;

import org.json.simple.JSONObject;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(nullable = false, unique = true, length = 11)
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

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean active;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column
    private String avatar;

    public User() {

    }

    public User(String idNumber, String name, String email, String lastName, String phone, String address, String password, boolean active, Set<Role> roles, String avatar) {
        this.idNumber = idNumber;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.avatar = avatar;
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

    public String getFullName() {
        return String.format("%s %s", this.name, this.lastName);
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

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean hasRole(String name) {

        boolean found = false;
        for (Role role : this.roles) {
            if (role.getRole().equals(name)) found = true;
        }

        return found;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String toJson() {

        JSONObject json = new JSONObject();

        json.put("id", this.id);
        json.put("idNumber", this.idNumber);
        json.put("name", this.name);
        json.put("lastName", this.lastName);
        json.put("email", this.email);
        json.put("phone", this.phone);
        json.put("address", this.address);
        json.put("active", this.active);
        json.put("role", this.roles.iterator().next().getRole());
        json.put("avatar", this.avatar);

        return json.toJSONString();
    }
}