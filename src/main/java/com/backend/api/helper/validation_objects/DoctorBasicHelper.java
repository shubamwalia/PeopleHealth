package com.backend.api.helper.validation_objects;

import com.backend.api.model.entity.Doctor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DoctorBasicHelper {

    private int id;

    @NotBlank(message = "required name")
    private String name;

    @NotBlank(message = "required qualification")
    private String qualification;

    @NotBlank(message = "required specialization")
    private String specialization;

    @NotBlank
    @Size(min = 10, max = 10, message = "invalid mobile number")
    private String mobileNumber;

    public DoctorBasicHelper() {
    }

    public DoctorBasicHelper(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.qualification = doctor.getQualification();
        this.specialization = doctor.getSpecialization();
        this.mobileNumber = doctor.getMobileNumber();
    }

    public DoctorBasicHelper(int id, String name, String qualification, String specialization, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.qualification = qualification;
        this.specialization = specialization;
        this.mobileNumber = mobileNumber;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
