package com.backend.api.helper.custom_validation_anotaions.anotaion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StartDateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InvalidDate {
    String message() default "entered start date is passed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
