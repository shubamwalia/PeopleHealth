package com.backend.api.helper.custom_validation_anotaions.anotaion;

import com.backend.api.model.service.IplanService;
import com.backend.api.model.sro.PlanSro;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

import static org.springframework.security.util.FieldUtils.getFieldValue;

public class MatchesValidator implements ConstraintValidator<Matches, Object>
{
   private String firstFieldName;
   private String secondFieldName;

   @Autowired
   private IplanService planService;

   @Override
   public void initialize(final Matches constraintAnnotation) {
      firstFieldName = constraintAnnotation.field();
      System.out.println(firstFieldName);
      System.out.println("abcabcabcabc");
      secondFieldName = constraintAnnotation.verifyField();
      System.out.println(secondFieldName);
   }

   @Override
   public boolean isValid(Object object, ConstraintValidatorContext context) {
      try {
         Object baseFieldValue = getFieldValue(object, firstFieldName);
         Object matchFieldValue = getFieldValue(object, secondFieldName);

         System.out.println("abcabcbacabc1234");
         System.out.println(baseFieldValue);
         System.out.println(matchFieldValue);
         PlanSro plan = planService.getPlan(Integer.parseInt((String) baseFieldValue));

         System.out.println(plan);
         System.out.println("abcabcbacabc123");


         if(Integer.parseInt((String) matchFieldValue) >= planService.checkTransactions(Integer.parseInt((String) baseFieldValue)) && plan.getPlanAmount() >= (Integer.parseInt((String) matchFieldValue) + plan.getAmountPaid())) {

            System.out.println("abcabcbacabc12345");
            return  true;

         }

         System.out.println("abcabcbacabc123456");
         /*PlanSro plan = planService.getPlan(Integer.parseInt(firstFieldName));

         System.out.println(plan);
         System.out.println("abcabcbacabc");

         if(Integer.parseInt(secondFieldName) >= planService.checkTransactions(Integer.parseInt(firstFieldName)) && plan.getPlanAmount() >= (Integer.parseInt(secondFieldName) + plan.getAmountPaid())) {

            return  true;

         }*/
         return false;

      } catch (Exception e) {
         // log error
         return false;
      }
   }

   private Object getFieldValue(Object object, String fieldName) throws Exception {
      Class<?> clazz = object.getClass();
      Field passwordField = clazz.getDeclaredField(fieldName);
      passwordField.setAccessible(true);
      return passwordField.get(object);
   }
}

