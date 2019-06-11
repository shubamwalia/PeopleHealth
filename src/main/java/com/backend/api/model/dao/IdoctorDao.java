package com.backend.api.model.dao;

import com.backend.api.model.entity.Doctor;

import java.util.List;

public interface IdoctorDao {

    Doctor doctorAdd(Doctor doctor);

    List<Object> doctorListBySpecialization(String specialization, Integer hospitalId, Integer start, Integer max);

    Doctor doctorGet(Integer doctorId);
}
