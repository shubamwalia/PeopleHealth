package com.backend.api.helper.custom_validation_anotaions.anotaion;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class FrequencyValidator implements ConstraintValidator<ValidFrequency, Integer> {

    @Override
    public void initialize(ValidFrequency constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return false;
        }
        if(s == 7 || s == 14 ||s == 30){
            return true;
        }
        return false;
    }
}
