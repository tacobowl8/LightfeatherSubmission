package com.lightfeather.challenge.submission;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) { return true; }
		if(value.isBlank()) { return true; }
		Pattern pattern = 
				Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$");
		Matcher matcher = pattern.matcher(value);
		try {
			if (!matcher.matches()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
