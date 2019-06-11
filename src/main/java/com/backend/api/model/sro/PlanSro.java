package com.backend.api.model.sro;

import com.backend.api.helper.custom_validation_anotaions.anotaion.InvalidDate;
import com.backend.api.helper.custom_validation_anotaions.anotaion.InvalidDownPayment;
import com.backend.api.helper.validation_objects.PlanRequest;
import com.backend.api.model.entity.Message;
import com.backend.api.model.entity.Plan;
import com.backend.api.helper.custom_validation_anotaions.anotaion.ValidFrequency;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class PlanSro {

    private int id;

    //@NotEmpty(message = "plan amount required")
    @Min(value = 1000, message = "minimum planAmount 1000")
    @Max(value = 100000, message = "maximum planAmount 100000")
    private int planAmount;

    //@NotEmpty(message = "intallment amount required")
    @Min(value = 500, message = "minimum installment amount 500")
    private int installmentAmount;

    //@NotEmpty(message = "plan frequency required")
    @ValidFrequency
    private int planFrequency;

    //@NotEmpty(message = "downpayment amount required")
    @Min(value = 500, message = "minimum downpayment amount 500")
    //@InvalidDownPayment(5)
    private int downPayment;

    private int amountPaid = 0;

    @Pattern(regexp = "(((0[1-9]|[12][0-9]|3[01])([-./])(0[13578]|10|12)([-./])(\\d{4}))|(([0][1-9]|[12][0-9]|30)([-./])(0[469]|11)([-./])(\\d{4}))|((0[1-9]|1[0-9]|2[0-8])([-./])(02)([-./])(\\d{4}))|((29)(\\.|-|\\/)(02)([-./])([02468][048]00))|((29)([-./])(02)([-./])([13579][26]00))|((29)([-./])(02)([-./])([0-9][0-9][0][48]))|((29)([-./])(02)([-./])([0-9][0-9][2468][048]))|((29)([-./])(02)([-./])([0-9][0-9][13579][26])))", message = "correct date format is dd/mm/yyyy and date should be valid")
    @InvalidDate
    private String startDate;

    private int dueAmount = 0;

    private String planStatus = "open";

    private DoctorSro doctor;

    @JsonIgnore
    private List<MessageSro> messagessro;

    public PlanSro() {
    }

    public PlanSro(Plan plan) {
        this.id = plan.getId();
        this.planAmount = plan.getPlanAmount();
        this.installmentAmount = plan.getInstallmentAmount();
        this.planFrequency = plan.getPlanFrequency();
        this.downPayment = plan.getDownPayment();
        this.amountPaid = plan.getAmountPaid();
        this.startDate = plan.getStartDate();
        this.dueAmount = plan.getDueAmount();
        this.planStatus = plan.getPlanStatus();
        messagesSroListUtil(plan.getMessages());
        this.doctor = new DoctorSro(plan.getDoctor());
    }

    public PlanSro(PlanRequest plan) {
        this.planAmount = plan.getPlanAmount();
        this.installmentAmount = plan.getInstallmentAmount();
        this.planFrequency = plan.getPlanFrequency();
        this.downPayment = plan.getDownPayment();
        this.startDate = plan.getStartDate();
        this.amountPaid = plan.getDownPayment();
        this.dueAmount = plan.getPlanAmount() - getDownPayment();
        this.planStatus = "open";
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

    public int getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(int installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public int getPlanFrequency() {
        return planFrequency;
    }

    public void setPlanFrequency(int planFrequency) {
        this.planFrequency = planFrequency;
    }

    public int getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(int downPayment) {
        this.downPayment = downPayment;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(int dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public List<MessageSro> getMessagessro() {
        return messagessro;
    }

    public void setMessagessro(List<MessageSro> messagessro) {
        this.messagessro = messagessro;
    }

    public DoctorSro getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorSro doctor) {
        this.doctor = doctor;
    }

    private void messagesSroListUtil(List<Message> messages) {
        for (Message message : messages) {
            MessageSro messagesro = new MessageSro(message);
            if (this.messagessro == null) {
                this.messagessro = new ArrayList<>();
            }
            this.messagessro.add(messagesro);
        }
    }

}