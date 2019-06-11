package com.backend.api.helper.validation_objects;

import com.backend.api.helper.custom_validation_anotaions.anotaion.InvalidDate;
import com.backend.api.helper.custom_validation_anotaions.anotaion.ValidFrequency;
import com.backend.api.model.entity.Message;
import com.backend.api.model.entity.Plan;
import com.backend.api.model.sro.DoctorSro;
import com.backend.api.model.sro.MessageSro;
import com.backend.api.model.sro.PlanSro;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class PlanRequest {

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
    private int downPayment;

    @Pattern(regexp = "(((0[1-9]|[12][0-9]|3[01])([-./])(0[13578]|10|12)([-./])(\\d{4}))|(([0][1-9]|[12][0-9]|30)([-./])(0[469]|11)([-./])(\\d{4}))|((0[1-9]|1[0-9]|2[0-8])([-./])(02)([-./])(\\d{4}))|((29)(\\.|-|\\/)(02)([-./])([02468][048]00))|((29)([-./])(02)([-./])([13579][26]00))|((29)([-./])(02)([-./])([0-9][0-9][0][48]))|((29)([-./])(02)([-./])([0-9][0-9][2468][048]))|((29)([-./])(02)([-./])([0-9][0-9][13579][26])))", message = "correct date format is dd/mm/yyyy and date should be valid")
    @InvalidDate
    private String startDate;

    public PlanRequest() {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
