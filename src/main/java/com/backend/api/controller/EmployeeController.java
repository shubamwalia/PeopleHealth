package com.backend.api.controller;

import com.backend.api.helper.response.BasicDataResponse;
import com.backend.api.helper.validation_objects.Signup;
import com.backend.api.model.service.IemployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Controller
@RequestMapping("/api")
public class EmployeeController {

    /**
     * Dependency injection from EmployeeService bean
     */

    @Autowired
    private IemployeeService employeeService;

    /**
     * For saving a Employee
     *
     * @param signup is details of employee
     * @return Object of BasicDataResponse which contains basic data of saved employee
     */
    @PostMapping(value = "/employees", consumes = "application/json")
    @ResponseBody
    public Object addEmployee(@RequestBody @Valid Signup signup) {
        BasicDataResponse employeeData = employeeService.addEmployee(signup);
        return employeeData;
    }

}
