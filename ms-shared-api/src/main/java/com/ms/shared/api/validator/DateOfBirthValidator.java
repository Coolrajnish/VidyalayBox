package com.ms.shared.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirth, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {

        if (date == null) {
            return false;
        }

        // get the current date and time
        Date currentDate = new Date();

        // calculate the difference between the two dates in milliseconds
        long difference = currentDate.getTime() - date.getTime();

        // calculate the difference in years
        long years = difference / (1000L * 60L * 60L * 24L * 365L);

        // if the difference is less than 10 years, return false
        return years >= 10;
    }
}