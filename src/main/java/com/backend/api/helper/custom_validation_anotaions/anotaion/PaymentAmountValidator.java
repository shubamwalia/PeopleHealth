package com.backend.api.helper.custom_validation_anotaions.anotaion;

import com.backend.api.model.service.IplanService;
import com.backend.api.model.sro.PlanSro;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PaymentAmountValidator implements ConstraintValidator<ValidPayment, Integer> {

   @Autowired
   private IplanService planService;

   public void initialize(ValidPayment constraint) {
   }

   public boolean isValid(Integer obj, ConstraintValidatorContext context) {
      return false;
   }

   /*PlanSro plan = planService.getPlan();*/

}
