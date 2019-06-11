package com.backend.api.helper.validation_objects;


import javax.validation.constraints.Size;

public class HospitalUpdateHelper {

    private String specialization;
    private String contactPersonName;

    @Size(min = 10, max = 10, message = "Invalid phone number")
    private String contactPersonPhone;

    public HospitalUpdateHelper() {
    }

    public HospitalUpdateHelper(String specialization, String contactPersonName, String contactPersonPhone) {
        this.specialization = specialization;
        this.contactPersonName = contactPersonName;
        this.contactPersonPhone = contactPersonPhone;
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
