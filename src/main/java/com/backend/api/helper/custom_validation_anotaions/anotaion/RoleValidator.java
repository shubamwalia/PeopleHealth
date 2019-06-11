package com.backend.api.helper.custom_validation_anotaions.anotaion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {
   public void initialize(ValidRole constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      if(obj == null){
         return false;
      }
      if(obj.matches("Customer|Employee")){
         return true;
      }
      return false;
   }
}
