package com.att.tdp.popcorn_palace.validation;

import com.att.tdp.popcorn_palace.model.Showtime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;

public class ShowtimeStartAndEndLimitValidator implements ConstraintValidator<ShowtimeStartAndEndLimit,Showtime> {
    OffsetDateTime startTime;
    OffsetDateTime endTime;

    @Override
    public void initialize(ShowtimeStartAndEndLimit constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Showtime showtime, ConstraintValidatorContext constraintValidatorContext) {
        startTime = showtime.getStartTime();
        endTime = showtime.getEndTime();
        if(startTime == null || endTime == null) {
            return false;
        }
        if (startTime.isAfter(endTime)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("End time must be after Start time").addConstraintViolation();
            return false;
        }
        return true;

    }
}
