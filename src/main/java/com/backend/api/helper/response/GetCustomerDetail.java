package com.backend.api.helper.response;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GetCustomerDetail {

    @NotNull(message = "required password")
    @Size(min = 1, max = 10, message = "Enter Valid phone number")
    private String phoneNumber;

    @NotNull(message = "required password")
    private String dob;

    public GetCustomerDetail() {
    }

    public GetCustomerDetail(String phoneNumber, String dob) {
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
