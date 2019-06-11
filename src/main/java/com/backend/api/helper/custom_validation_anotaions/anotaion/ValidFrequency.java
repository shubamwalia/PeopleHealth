package com.backend.api.helper.custom_validation_anotaions.anotaion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FrequencyValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFrequency {
    String message() default "frequency can be 7, 14 or 30";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
