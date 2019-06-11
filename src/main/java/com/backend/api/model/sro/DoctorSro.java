package com.backend.api.model.sro;

import com.backend.api.model.entity.Customer;
import com.backend.api.model.entity.Doctor;

import java.util.HashSet;
import java.util.Set;

public class DoctorSro {

    private int id;

    private String name;

    private String qualification;

    private String specialization;

    private String mobileNumber;

    private Set<CustomerSro> customerSroSet;

    public DoctorSro() {
    }

    public DoctorSro(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.qualification = doctor.getQualification();
        this.specialization = doctor.getSpecialization();
        this.mobileNumber = doctor.getMobileNumber();
        customerSroSetUtil(doctor.getCustomerSet());
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

    public void customerSroSetUtil(Set<Customer> customerSet) {
        for (Customer customer : customerSet) {
            if (customerSroSet == null) {
                this.customerSroSet = new HashSet<>();
            }
            CustomerSro customerSro = new CustomerSro(customer);
            customerSroSet.add(customerSro);
        }
    }

    @Override
    public String toString() {
        return "DoctorSro{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qualification='" + qualification + '\'' +
                ", specialization='" + specialization + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", customerSroSet=" + customerSroSet +
                '}';
    }
}
