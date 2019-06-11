package com.backend.api.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl = "https://s3.us-east-2.amazonaws.com/backend-photos-assignment/profile/default-avatar.jpg";

    @Column(name = "openPlan", nullable = false)
    private int openPlan = 0;

    @Column(name = "closedPlan", nullable = false)
    private int closedPlan = 0;

    @Column(name = "cancelledPlan", nullable = false)
    private int cancelledPlan = 0;

    @Column(name = "pendingPlan", nullable = false)
    private int pendingPlan = 0;

    @Column(name = "totalPlan", nullable = false)
    private int totalPlan = 0;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerDetail customerDetail;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Doctor> doctorList;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Plan> planList;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Customer() {
        openPlan = 0;
        closedPlan = 0;
        cancelledPlan = 0;
        pendingPlan = 0;
        totalPlan = 0;
    }

    public Customer(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Customer(String name, String password, String city, CustomerDetail customerDetail) {
        this.name = name;
        this.city = city;
        this.customerDetail = customerDetail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

    public int getTotalPlan() {
        return totalPlan;
    }

    public void setTotalPlan(int totalPlan) {
        this.totalPlan = totalPlan;
    }

    public int getOpenPlan() {
        return openPlan;
    }

    public void setOpenPlan(int openPlan) {
        this.openPlan = openPlan;
    }

    public int getClosedPlan() {
        return closedPlan;
    }

    public void setClosedPlan(int closedPlan) {
        this.closedPlan = closedPlan;
    }

    public int getCancelledPlan() {
        return cancelledPlan;
    }

    public void setCancelledPlan(int cancelledPlan) {
        this.cancelledPlan = cancelledPlan;
    }

    public int getPendingPlan() {
        return pendingPlan;
    }

    public void setPendingPlan(int pendingPlan) {
        this.pendingPlan = pendingPlan;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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


    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Set<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(Set<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public Set<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(Set<Plan> planList) {
        this.planList = planList;
    }

    public void addDoctorUtil(Doctor doctor) {
        if (doctorList == null) {
            doctorList = new HashSet<>();
        }
        doctorList.add(doctor);
    }

    public void addPlans(Plan plan) {
        if (planList == null) {
            planList = new HashSet<>();
        }
        planList.add(plan);
    }


}




