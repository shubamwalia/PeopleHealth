package com.backend.api.model.sro;

import com.backend.api.model.entity.Customer;
import com.backend.api.model.entity.CustomerDetail;
import com.backend.api.model.entity.Plan;

import java.util.HashSet;
import java.util.Set;

public class CustomerSro {
    private int id;

    private String name;

    private String city;

    private String profilePhotoUrl = "https://s3.us-east-2.amazonaws.com/backend-photos-assignment/profile/default-avatar.jpg";

    private int openPlan = 0;

    private int closedPlan = 0;

    private int cancelledPlan = 0;

    private int pendingPlan = 0;

    private int totalPlan = 0;

    private String phn_no;

    private String dob;

    private Set<PlanSro> planSroList;

    public CustomerSro() {
    }


    public CustomerSro(Customer customer, CustomerDetail customerDetail) {

        this.id = customer.getId();
        this.name = customer.getName();
        this.profilePhotoUrl = customer.getProfilePhotoUrl();
        this.city = customer.getCity();
        this.cancelledPlan = customer.getCancelledPlan();
        this.closedPlan = customer.getClosedPlan();
        this.openPlan = customer.getOpenPlan();
        this.pendingPlan = customer.getPendingPlan();
        this.phn_no = customerDetail.getPhn_no();
        this.dob = customerDetail.getDob();
        this.totalPlan = customer.getTotalPlan();
        this.city = customer.getCity();

        if (customer.getPlanList() != null)
            customerSroSetUtility(customer.getPlanList());
        else
            this.planSroList = null;
    }

    public CustomerSro(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.city = customer.getCity();
        this.profilePhotoUrl = customer.getProfilePhotoUrl();
        this.openPlan = customer.getOpenPlan();
        this.closedPlan = customer.getClosedPlan();
        this.cancelledPlan = customer.getCancelledPlan();
        this.pendingPlan = customer.getPendingPlan();
        this.totalPlan = customer.getTotalPlan();
        /*f (customer.getPlanList() != null)
            customerSroSetUtility(customer.getPlanList());
        else
            this.planSroList = null;*/
    }

    private void customerSroSetUtility(Set<Plan> planList) {
        for (Plan plan : planList) {
            PlanSro planSro = new PlanSro(plan);
            if (this.planSroList == null) this.planSroList = new HashSet<>();

            this.planSroList.add(planSro);
        }
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

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
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

    public String getPhn_no() {
        return phn_no;
    }

    public void setPhn_no(String phn_no) {
        this.phn_no = phn_no;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Set<PlanSro> getPlanSroList() {
        return planSroList;
    }

    public void setPlanSroList(Set<PlanSro> planSroList) {
        this.planSroList = planSroList;
    }
}
