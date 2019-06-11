package com.backend.api.model.dao.impl;

import com.backend.api.model.dao.IemployeeDao;
import com.backend.api.model.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements IemployeeDao {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * For saving a Employee
     *
     * @param employee is Employee object which cotains details of employee
     * @return Object of employee which contains data of saved employee
     */
    @Override
    public Employee saveOrUpdateEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
        return employee;
    }

}
