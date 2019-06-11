package com.backend.api.helper.custom_validation_anotaions.anotaion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DownPaymentValidator implements ConstraintValidator<InvalidDownPayment, String> {
   public void initialize(InvalidDownPayment constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {

     // plan.getDownPayment() > plan.getPlanAmount() || plan.getInstallmentAmount() > plan.getPlanAmount() || ((plan.getInstallmentAmount() > plan.getPlanAmount() - plan.getDownPayment()) && (plan.getPlanAmount() != plan.getDownPayment()))

      return false;
   }
}
