package com.backend.api.helper.custom_validation_anotaions.anotaion;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DownPaymentValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InvalidDownPayment {
    String message() default "entered start date is passed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    long value();

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        Min[] value();
    }
}
