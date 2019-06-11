package com.backend.api.helper.custom_validation_anotaions.anotaion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "role can be Customer or Employee";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
