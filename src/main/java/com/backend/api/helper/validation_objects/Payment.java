package com.backend.api.helper.validation_objects;

import com.backend.api.helper.custom_validation_anotaions.anotaion.Matches;
import com.backend.api.helper.custom_validation_anotaions.anotaion.ValidPayment;

import javax.validation.constraints.NotBlank;

//@Matches(field="planId", verifyField="amount", message = "abc")
public class Payment {

    //private String planId;

    //@ValidPayment
    @NotBlank
    private String amount;

    /*public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }*/

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
