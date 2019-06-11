package com.backend.api.model.entity;


import com.backend.api.model.sro.HospitalSro;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hospital")
public class Hospital {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    private String specialization;

    private String contactPersonName;

    private String contactPersonPhone;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "hospital_doctor")
    private Set<Doctor> doctorSet;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Plan> planSet;

    public Hospital() {
    }

    public Hospital(HospitalSro hospitalSro) {

        this.id = hospitalSro.getId();
        this.name = hospitalSro.getName();
        this.city = hospitalSro.getCity();
        this.specialization = hospitalSro.getSpecialization();
        this.contactPersonName = hospitalSro.getContactPersonName();
        this.contactPersonPhone = hospitalSro.getContactPersonPhone();
    }

    public Hospital(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Hospital(String name, String city, String specialization, String contactPersonName, String contactPersonPhone) {
        this.name = name;
        this.city = city;
        this.specialization = specialization;
        this.contactPersonName = contactPersonName;
        this.contactPersonPhone = contactPersonPhone;
    }

    public Hospital(String name, String city, String specialization, String contactPersonName, String contactPersonPhone, User user, Set<Doctor> doctorSet, Set<Plan> planSet) {
        this.name = name;
        this.city = city;
        this.specialization = specialization;
        this.contactPersonName = contactPersonName;
        this.contactPersonPhone = contactPersonPhone;
        this.user = user;
        this.doctorSet = doctorSet;
        this.planSet = planSet;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Doctor> getDoctorSet() {
        return doctorSet;
    }

    public void setDoctorSet(Set<Doctor> doctorSet) {
        this.doctorSet = doctorSet;
    }

    public Set<Plan> getPlanSet() {
        return planSet;
    }

    public void setPlanSet(Set<Plan> planSet) {
        this.planSet = planSet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addDoctorUtil(Doctor doctor) {
        if (this.doctorSet == null) {
            this.doctorSet = new HashSet<>();
        }
        this.doctorSet.add(doctor);
    }

    public void addPlanUtil(Plan plan) {
        if (this.planSet == null) {
            this.planSet = new HashSet<>();
        }
        this.planSet.add(plan);
    }
}
