package com.example.Users.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Users.service.UserService;

public class LoginIdUniqueValidator implements ConstraintValidator<Unique,String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(Unique unique) {
        unique.message();
    }

	@Override
	public boolean isValid(String loginId, ConstraintValidatorContext arg1) {
		 if (userService != null && userService.existsByLoginId(loginId)) {
	            return false;
	        }
	        return true;
	}
}