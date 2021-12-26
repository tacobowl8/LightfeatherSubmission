package com.lightfeather.challenge.submission;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Documented
public @interface PhoneNumber {
	String message() default "{PhoneNumber.invalid}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
