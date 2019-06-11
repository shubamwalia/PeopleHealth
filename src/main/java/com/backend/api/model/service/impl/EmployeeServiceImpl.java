package com.backend.api.model.service.impl;

import com.backend.api.helper.miscellaneous.UserConstants;
import com.backend.api.helper.response.BasicDataResponse;
import com.backend.api.helper.validation_objects.Signup;
import com.backend.api.model.dao.IemployeeDao;
import com.backend.api.model.entity.Employee;
import com.backend.api.model.entity.User;
import com.backend.api.model.service.IemployeeService;
import com.backend.api.model.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements IemployeeService {

    /**
     * Dependency injection from Employee Dao bean
     */
    @Autowired
    private IemployeeDao employeeDao;

    /**
     * Dependency injection from User Service bean
     */
    @Autowired
    private IuserService iuserService;

    /**
     * For saving a Employee
     *
     * @param signup is details of employee
     * @return Object of BasicDataResponse which contains basic data of saved employee
     */
    @Override
    @Transactional
    public BasicDataResponse addEmployee(Signup signup) {

        Employee employee = new Employee(signup.getName(), signup.getCity());

        User userEncoded = iuserService.addUser(new User(signup, UserConstants.ENABLE));
        employee.setUser(userEncoded);

        Employee employee1 = employeeDao.saveOrUpdateEmployee(employee);

        signup.setId(employee.getId());
        BasicDataResponse basicDataResponse = new BasicDataResponse(signup);

        return basicDataResponse;
    }

}
