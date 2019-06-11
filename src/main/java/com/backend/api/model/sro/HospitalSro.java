package com.backend.api.model.sro;

import com.backend.api.model.entity.Doctor;
import com.backend.api.model.entity.Hospital;
import com.backend.api.model.entity.Plan;

import java.util.HashSet;
import java.util.Set;

public class HospitalSro {

    private int id;
    private String name;
    private String city;
    private String specialization;
    private String contactPersonName;
    private String contactPersonPhone;
    private UserSro user;
    private Set<DoctorSro> doctorSet;
    private Set<PlanSro> planSet;

    public HospitalSro() {
    }

    public HospitalSro(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.city = hospital.getCity();
        this.specialization = hospital.getSpecialization();
        this.contactPersonName = hospital.getContactPersonName();
        this.contactPersonPhone = hospital.getContactPersonPhone();
        this.user = new UserSro(hospital.getUser());
        doctorSetUtil(hospital.getDoctorSet());
        planSetUtil(hospital.getPlanSet());

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

    public UserSro getUser() {
        return user;
    }

    public void setUser(UserSro user) {
        this.user = user;
    }

    public Set<DoctorSro> getDoctorSet() {
        return doctorSet;
    }

    public void setDoctorSet(Set<DoctorSro> doctorSet) {
        this.doctorSet = doctorSet;
    }

    public Set<PlanSro> getPlanSet() {
        return planSet;
    }

    public void setPlanSet(Set<PlanSro> planSet) {
        this.planSet = planSet;
    }


    public void doctorSetUtil(Set<Doctor> doctorHashSet) {
        for (Doctor doctor : doctorHashSet) {
            if (this.doctorSet == null) {
                this.doctorSet = new HashSet<>();
            }
            DoctorSro doctorSro = new DoctorSro(doctor);
            this.doctorSet.add(doctorSro);
        }
    }

    public void planSetUtil(Set<Plan> planHashSet) {
        for (Plan plan : planHashSet) {
            if (this.planSet == null) {
                this.planSet = new HashSet<>();
            }
            PlanSro planSro = new PlanSro(plan);
            this.planSet.add(planSro);
        }
    }
}
