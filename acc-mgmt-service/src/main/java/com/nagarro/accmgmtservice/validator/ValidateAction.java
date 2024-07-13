package com.nagarro.accmgmtservice.validator;


import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
@Documented
@Constraint(validatedBy = ActionValidator.class)
public @interface ValidateAction {
	
	 public String message() default "Invalid Action Type: It should be either Deposit Or Withdraw";

	 Class<?>[] groups() default {};

	 Class<? extends Payload>[] payload() default {};
}
