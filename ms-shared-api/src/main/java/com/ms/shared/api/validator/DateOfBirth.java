package com.ms.shared.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateOfBirthValidator.class)
public @interface DateOfBirth {

    String message() default "Invalid date of birth. Must be at least 10 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
