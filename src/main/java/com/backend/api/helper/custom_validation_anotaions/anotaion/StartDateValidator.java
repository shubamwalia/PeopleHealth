package com.backend.api.helper.custom_validation_anotaions.anotaion;

import com.backend.api.exceptionhandler.InvalidDateException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StartDateValidator implements ConstraintValidator<InvalidDate, String> {
   public void initialize(InvalidDate constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {

      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");

      Date startDate = null;

      if(obj == null) return true;
      try {
         startDate = dateFormat.parse(obj);
      } catch (ParseException e) {
         e.printStackTrace();
      }

      if(startDate.before(new Date())) {

         return false;

      }

      return true;
   }
}
