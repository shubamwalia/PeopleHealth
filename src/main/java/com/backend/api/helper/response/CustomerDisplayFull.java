package com.backend.api.helper.response;

import com.backend.api.model.entity.Customer;
import com.backend.api.model.entity.CustomerDetail;
import com.backend.api.model.sro.CustomerSro;
import com.backend.api.model.sro.PlanSro;

import java.util.HashSet;
import java.util.Set;

public class CustomerDisplayFull {

    private int id;
    private String name;
    // private String email;
    private String city;
    private String phoneNumber;
    private String dob;
    // private List<Doctor> doctorList;
    private Set<PlanSro> planList;
    private String image;
    private int openPlan = 0;
    private int closedPlan = 0;
    private int cancelledPlan = 0;
    private int pendingPlan = 0;
    private int totalPlan = 0;

    public CustomerDisplayFull() {
    }

    public CustomerDisplayFull(CustomerSro customerSro) {
        this.id = customerSro.getId();
        this.name = customerSro.getName();
        //   this.email = customerSro.
        this.city = customerSro.getCity();
        this.phoneNumber = customerSro.getPhn_no();
        this.dob = customerSro.getDob();
        this.image = customerSro.getProfilePhotoUrl();
        this.planList = customerSro.getPlanSroList();
        this.openPlan = customerSro.getOpenPlan();
        this.closedPlan = customerSro.getClosedPlan();
        this.cancelledPlan = customerSro.getCancelledPlan();
        this.pendingPlan = customerSro.getPendingPlan();
        this.totalPlan = customerSro.getTotalPlan();
    }

    public CustomerDisplayFull(Customer customer, CustomerDetail customerDetail) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.city = customer.getCity();
        this.image = customer.getProfilePhotoUrl();
        this.phoneNumber = customerDetail.getPhn_no();
        this.dob = customerDetail.getDob();
        this.openPlan = customer.getOpenPlan();
        this.closedPlan = customer.getClosedPlan();
        this.cancelledPlan = customer.getCancelledPlan();
        this.pendingPlan = customer.getPendingPlan();
        this.totalPlan = customer.getTotalPlan();
        //this.planList = customer.getPlanList();
        // customerDisplayListUtility(customer.getPlanList());
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

    /*   public String getEmail() {
           return email;
       }

       public void setEmail(String email) {
           this.email = email;
       }
   */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

 /*   public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }*/

    public Set<PlanSro> getPlanList() {
        return planList;
    }

    public void setPlanList(Set<PlanSro> planList) {
        this.planList = planList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getTotalPlan() {
        return totalPlan;
    }

    public void setTotalPlan(int totalPlan) {
        this.totalPlan = totalPlan;
    }

    public void customerDisplayListUtility(Set<PlanSro> planSroList) {
        for (PlanSro plan : planSroList) {
            // PlanSro planSro = new PlanSro(plan);
            if (planList == null) planList = new HashSet<>();
            this.planList.add(plan);
        }

    }
}
