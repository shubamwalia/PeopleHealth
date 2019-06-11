package com.backend.api.model.dao;


import com.backend.api.model.entity.Employee;

public interface IemployeeDao {
    Employee saveOrUpdateEmployee(Employee employee);
}
