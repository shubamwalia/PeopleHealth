package com.backend.api.model.service.impl;

import com.backend.api.exceptionhandler.NoDataFoundException;
import com.backend.api.helper.miscellaneous.UserConstants;
import com.backend.api.helper.response.BasicDataResponse;
import com.backend.api.helper.response.CitiesStaticList;
import com.backend.api.helper.response.HospitalListResponse;
import com.backend.api.helper.response.SpecializationStaticList;
import com.backend.api.helper.validation_objects.DoctorBasicHelper;
import com.backend.api.helper.validation_objects.HospitalUpdateHelper;
import com.backend.api.helper.validation_objects.Signup;
import com.backend.api.model.dao.IhospitalDao;
import com.backend.api.model.entity.Doctor;
import com.backend.api.model.entity.Hospital;
import com.backend.api.model.entity.User;
import com.backend.api.model.service.IhospitalService;
import com.backend.api.model.service.IuserService;
import com.backend.api.model.sro.HospitalSro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalServiceImpl implements IhospitalService {


    /**
     * dependency injection for hospital dao
     */
    @Autowired
    private IhospitalDao ihospitalDao;


    /**
     * dependency injection for user service
     */
    @Autowired
    private IuserService iuserService;


    /**
     * for adding hospital
     * @param signup contains necessary data needed for registration
     * @return BasicDataResponse object
     * @see (BasicDataResponse)
     */
    @Override
    @Transactional
    public BasicDataResponse hospitalAdd(Signup signup) {

        Hospital hospital = new Hospital(signup.getName(), signup.getCity());

        User userEncoded = iuserService.addUser(new User(signup, UserConstants.DISABLE));
        hospital.setUser(userEncoded);

        ihospitalDao.hospitalSaveorUpdate(hospital);

        signup.setId(hospital.getId());
        BasicDataResponse basicDataResponse = new BasicDataResponse(signup,userEncoded.getEnable());

        return basicDataResponse;
    }


    /**
     * for updating hospital
     * @param hospitalId contains hospital id which needs to be update
     * @param hospitalUpdateHelper contains data for update
     * @return HospitalUpdateHelper object
     * @see (HospitalUpdateHelper)
     */
    @Override
    @Transactional
    public HospitalUpdateHelper hospitalUpdate(Integer hospitalId, HospitalUpdateHelper hospitalUpdateHelper) {

        String contactPersonName = hospitalUpdateHelper.getContactPersonName();
        String contactPersonPhone = hospitalUpdateHelper.getContactPersonPhone();
        String specialization = hospitalUpdateHelper.getSpecialization();

        Hospital hospital = ihospitalDao.getHospital(hospitalId);

        if (contactPersonName != null) {
            hospital.setContactPersonName(contactPersonName);
        }
        if (contactPersonPhone != null) {
            hospital.setContactPersonPhone(contactPersonPhone);
        }
        if (specialization != null) {
            hospital.setSpecialization(specialization);
        }

        ihospitalDao.hospitalSaveorUpdate(hospital);

        return hospitalUpdateHelper;
    }


    /**
     * for getting hospital by hospital id
     * @param hospitalId contains hospital id
     * @return HospitalSro object contains all data related to hospital
     * @see (HospitalSro)
     */
    @Override
    @Transactional
    public HospitalSro hospitalGet(Integer hospitalId){

        Hospital hospital = ihospitalDao.getHospital(hospitalId);

        if (hospital == null){
            throw new NoDataFoundException("hospital data not found");
        }

        HospitalSro hospitalSro = new HospitalSro(hospital);
        return hospitalSro;
    }


    /**
     * for getting hospitals by city
     * @param city contains city
     * @param start needed for providing starting index in pagination
     * @param max needed for providing maximum results per request in pagination
     * @return HospitalListResponse object contains eager data about hospital
     * @see HospitalListResponse
     */
    @Override
    @Transactional
    public List<HospitalListResponse> hospitalListByCity(String city, Integer start, Integer max) {

        List<Hospital> hospitalListByCity = ihospitalDao.hospitalListByCity(city, start, max);

        if (hospitalListByCity == null){
                throw new NoDataFoundException("hospital data not found");
        }

        List<HospitalListResponse> hospitalListResponseList = new ArrayList<>();

        for (Hospital hospital : hospitalListByCity) {
            HospitalListResponse hospitalListResponse = new HospitalListResponse(hospital);
            hospitalListResponseList.add(hospitalListResponse);
        }

        return hospitalListResponseList;
    }


    /**
     * for getting all hospital list
     * @param start needed for providing starting index in pagination
     * @param max needed for providing maximum results per request in pagination
     * @return HospitalListResponse object contains eager data about hospital
     * @see HospitalListResponse
     */
    @Override
    @Transactional
    public List<HospitalListResponse> hospitalList(Integer start, Integer max) {
        List<Hospital> hospitalList = ihospitalDao.hospitalList(start, max);

        if (hospitalList == null){
            throw new NoDataFoundException("hospital data not found ");
        }

        List<HospitalListResponse> hospitalListResponseList = new ArrayList<>();

        for (Hospital hospital : hospitalList) {
            HospitalListResponse hospitalListResponse = new HospitalListResponse(hospital);
            hospitalListResponseList.add(hospitalListResponse);
        }

        return hospitalListResponseList;
    }


    /**
     * for getting doctor list by hospital id
     * @param hospitalId contains hospital id
     * @param start needed for providing starting index in pagination
     * @param max needed for providing maximum results per request in pagination
     * @return DoctorBasicHelper object containing basic data of doctor
     * @see DoctorBasicHelper
     */
    @Override
    @Transactional
    public List<DoctorBasicHelper> doctorsListByHospitalId(Integer hospitalId, Integer start, Integer max) {

        List<Doctor> doctorList = ihospitalDao.doctorsListByHospitalId(hospitalId, start, max);

        if (doctorList == null){
            throw new NoDataFoundException("hospital data not found");
        }

        List<DoctorBasicHelper> doctorBasicHelperList = new ArrayList<>();

        for (Doctor doctor : doctorList) {
            doctorBasicHelperList.add(new DoctorBasicHelper(doctor));
        }

        return doctorBasicHelperList;
    }


    /**
     * for getting static cities
     * @return list of cities
     */
    @Override
    public List<String> hospitalCities() {
        List<String> stringList = new ArrayList<>();
        for (CitiesStaticList citiesStaticList : CitiesStaticList.values()) {
            stringList.add(citiesStaticList.toString());
        }
        return stringList;
    }


    /**
     * for getting static specializations
     * @return list of specializations
     */
    @Override
    public List<String> hospitalSpecializations() {
        List<String> stringList = new ArrayList<>();
        for (SpecializationStaticList specializationStaticList : SpecializationStaticList.values()) {
            stringList.add(specializationStaticList.toString());
        }
        return stringList;
    }
}
