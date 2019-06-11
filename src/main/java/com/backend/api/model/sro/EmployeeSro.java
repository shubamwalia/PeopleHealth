package com.backend.api.model.sro;

import com.backend.api.model.entity.Employee;

public class EmployeeSro {

    private int id;
    private String name;
    private String city;
    private UserSro userSro;

    public EmployeeSro() {
    }

    public EmployeeSro(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.city = employee.getCity();
        this.userSro = new UserSro(employee.getUser());
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

    public UserSro getUserSro() {
        return userSro;
    }

    public void setUserSro(UserSro userSro) {
        this.userSro = userSro;
    }
}
