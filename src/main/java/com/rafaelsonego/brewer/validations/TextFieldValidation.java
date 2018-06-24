package com.rafaelsonego.brewer.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "(^[a-zA-Z\\s]*$)?")
public @interface TextFieldValidation {

	@OverridesAttribute(constraint = Pattern.class, name="message")
	String message() default "Accepts only letters between A and Z";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
