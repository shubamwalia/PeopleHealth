package com.backend.api.helper.response;

import com.backend.api.model.entity.Hospital;

public class HospitalListResponse {

    private int id;
    private String name;
    private String city;
    private String specialization;
    private String contactPersonName;
    private String contactPersonPhone;

    public HospitalListResponse(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.city = hospital.getCity();
        this.specialization = hospital.getSpecialization();
        this.contactPersonName = hospital.getContactPersonName();
        this.contactPersonPhone = hospital.getContactPersonPhone();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }
}
