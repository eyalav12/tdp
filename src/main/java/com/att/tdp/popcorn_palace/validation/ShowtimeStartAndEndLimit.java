package com.att.tdp.popcorn_palace.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ShowtimeStartAndEndLimitValidator.class)
public @interface ShowtimeStartAndEndLimit {
    String message() default "End Time must be after Start Time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
