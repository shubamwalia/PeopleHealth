package com.backend.api.model.entity;

import com.backend.api.helper.validation_objects.DoctorBasicHelper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String qualification;
    @Column(nullable = false)
    private String specialization;
    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private Set<Customer> customerSet;

    public Doctor() {
    }

    public Doctor(DoctorBasicHelper doctorBasicHelper) {
        this.name = doctorBasicHelper.getName();
        this.qualification = doctorBasicHelper.getQualification();
        this.specialization = doctorBasicHelper.getSpecialization();
        this.mobileNumber = doctorBasicHelper.getMobileNumber();
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


    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    public void addCustomertil(Customer customer) {
        if (customerSet == null) {
            customerSet = new HashSet<>();
        }
        customerSet.add(customer);
    }

}
