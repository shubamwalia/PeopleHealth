package com.backend.api.model.entity;

import com.backend.api.model.sro.PlanSro;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(1000)
    @Max(100000)
    @Column(nullable = false)
    private int planAmount;

    @Min(500)
    @Column(nullable = false)
    private int installmentAmount;

    @Column(nullable = false)
    private int planFrequency;

    @Min(500)
    @Column(nullable = false)
    private int downPayment;

    @Column(nullable = false)
    private int amountPaid = 0;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private int dueAmount = 0;

    @Column(nullable = false)
    private String planStatus = "open";

    @ManyToOne(cascade = CascadeType.ALL)
    private Doctor doctor;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;

    public Plan() {
    }

    public Plan(PlanSro plan) {
        this.planAmount = plan.getPlanAmount();
        this.installmentAmount = plan.getInstallmentAmount();
        this.planFrequency = plan.getPlanFrequency();
        this.downPayment = plan.getDownPayment();
        this.amountPaid = plan.getAmountPaid();
        this.startDate = plan.getStartDate();
        this.dueAmount = plan.getDueAmount();
        this.planStatus = plan.getPlanStatus();
    }

    public int getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(int dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(int installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public int getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(int downPayment) {
        this.downPayment = downPayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(int planAmount) {
        this.planAmount = planAmount;
    }

    public int getPlanFrequency() {
        return planFrequency;
    }

    public void setPlanFrequency(int planFrequency) {
        this.planFrequency = planFrequency;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessageUtil(Message message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
    }
}