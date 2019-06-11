package com.backend.api.model.service.impl;


import com.backend.api.exceptionhandler.NoDataFoundException;
import com.backend.api.helper.validation_objects.DoctorBasicHelper;
import com.backend.api.model.dao.IdoctorDao;
import com.backend.api.model.dao.IhospitalDao;
import com.backend.api.model.entity.Doctor;
import com.backend.api.model.entity.Hospital;
import com.backend.api.model.service.IdoctorService;
import com.backend.api.model.sro.DoctorSro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements IdoctorService {

    @Autowired
    IdoctorDao idoctorDao;

    @Autowired
    IhospitalDao ihospitalDao;

    @Override
    @Transactional
    public DoctorBasicHelper doctorAdd(Integer hospitalId, DoctorBasicHelper doctorBasicHelper) {

        Doctor doctor = new Doctor(doctorBasicHelper);

        DoctorBasicHelper doctorBasicHelperResponse = new DoctorBasicHelper(idoctorDao.doctorAdd(doctor));
        doctorBasicHelperResponse.setId(doctor.getId());

        Hospital hospital = ihospitalDao.getHospital(hospitalId);
        hospital.addDoctorUtil(doctor);

        return doctorBasicHelperResponse;
    }

    @Override
    @Transactional
    public List<DoctorBasicHelper> doctorListBySpecialization(String specialization, Integer hospitalId, Integer start, Integer max) {

        List<Object> doctorList = idoctorDao.doctorListBySpecialization(specialization, hospitalId, start, max);

        if (doctorList == null){
            throw new NoDataFoundException("doctor data not found");
        }

        Object[] objectsResponse = doctorList.toArray();
        List<DoctorBasicHelper> doctorBasicHelperList = new ArrayList<>();

        for (Object objects : objectsResponse) {
            Object[] object = (Object[]) objects;
            Integer id = (Integer) object[0];
            String mobileNumber = (String) object[1];
            String name = (String) object[2];
            String qualification = (String) object[3];
            String specializationObject = (String) object[4];

            doctorBasicHelperList.add(new DoctorBasicHelper(id, name, qualification, specializationObject, mobileNumber));
        }

        return doctorBasicHelperList;
    }

    @Override
    @Transactional
    public DoctorSro doctorGet(Integer doctorId) {

        Doctor doctor = idoctorDao.doctorGet(doctorId);

        if (doctor == null){
            throw new NoDataFoundException("doctor data not found");
        }

        DoctorSro doctorSro = new DoctorSro(doctor);

        return doctorSro;
    }
}
