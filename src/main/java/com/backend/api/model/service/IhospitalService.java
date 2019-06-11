package com.backend.api.model.service;

import com.backend.api.helper.response.BasicDataResponse;
import com.backend.api.helper.response.HospitalListResponse;
import com.backend.api.helper.validation_objects.DoctorBasicHelper;
import com.backend.api.helper.validation_objects.HospitalUpdateHelper;
import com.backend.api.helper.validation_objects.Signup;
import com.backend.api.model.sro.HospitalSro;

import java.util.List;

public interface IhospitalService {

    BasicDataResponse hospitalAdd(Signup signup);

    HospitalUpdateHelper hospitalUpdate(Integer hospitalId, HospitalUpdateHelper hospitalUpdateHelper);

    HospitalSro hospitalGet(Integer hospitalId);

    List<HospitalListResponse> hospitalListByCity(String city, Integer start, Integer max);

    List<HospitalListResponse> hospitalList(Integer start, Integer max);

    List<DoctorBasicHelper> doctorsListByHospitalId(Integer hospitalId, Integer start, Integer max);

    List<String> hospitalCities();

    List<String> hospitalSpecializations();
}
