package com.backend.api.model.service;

import com.backend.api.helper.response.BasicDataResponse;
import com.backend.api.helper.validation_objects.Signup;

public interface IemployeeService {
    BasicDataResponse addEmployee(Signup signup);
}
