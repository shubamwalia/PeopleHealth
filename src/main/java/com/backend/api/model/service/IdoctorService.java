package com.backend.api.model.service;

import com.backend.api.helper.validation_objects.DoctorBasicHelper;
import com.backend.api.model.sro.DoctorSro;

import java.util.List;

public interface IdoctorService {

    DoctorBasicHelper doctorAdd(Integer hospitalId, DoctorBasicHelper doctorBasicHelper);

    List<DoctorBasicHelper> doctorListBySpecialization(String specialization, Integer hospitalId, Integer start, Integer max);

    DoctorSro doctorGet(Integer doctorId);
}
