package com.backend.api.model.entity;

import com.backend.api.model.sro.EmployeeSro;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String city;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;


    public Employee(EmployeeSro employeeSro) {
        this.name = employeeSro.getName();
        this.city = employeeSro.getCity();
    }

    public Employee(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}