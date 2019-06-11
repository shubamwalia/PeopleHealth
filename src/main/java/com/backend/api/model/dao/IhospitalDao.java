package com.backend.api.model.dao;

import com.backend.api.model.entity.Doctor;
import com.backend.api.model.entity.Hospital;

import java.util.List;

public interface IhospitalDao {

    Hospital hospitalSaveorUpdate(Hospital hospital);

    Hospital getHospital(Integer id);

    List<Hospital> hospitalListByCity(String city, Integer start, Integer max);

    List<Hospital> hospitalList(Integer start, Integer max);

    List<Doctor> doctorsListByHospitalId(Integer hospitalId, Integer start, Integer max);
}
