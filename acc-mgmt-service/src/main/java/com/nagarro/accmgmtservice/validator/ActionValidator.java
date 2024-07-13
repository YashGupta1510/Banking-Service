package com.nagarro.accmgmtservice.validator;
import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ActionValidator implements ConstraintValidator<ValidateAction, String>{

	@Override
	public boolean isValid(String action, ConstraintValidatorContext context) {
		List<String> sortOrderList = Arrays.asList("deposit", "withdraw");
        return sortOrderList.contains(action.toLowerCase());
	}

	
}
