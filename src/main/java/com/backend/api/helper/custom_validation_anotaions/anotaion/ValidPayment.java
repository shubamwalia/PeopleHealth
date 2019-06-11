package com.backend.api.helper.custom_validation_anotaions.anotaion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PaymentAmountValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPayment {
    String message() default "entered amount is wrong";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
