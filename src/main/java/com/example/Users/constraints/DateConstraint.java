package com.example.Users.constraints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraint implements ConstraintValidator<CustomDateValidator, String>{

	private String dateFormat;
	 
    public DateConstraint(String dateFormat) {
        this.dateFormat = dateFormat;
    }


	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		
      
		final List<String> dateFormats = Arrays.asList("yyyy-MMM-dd", "dd-MM-yyyy");    

	    for(String format: dateFormats){
	        SimpleDateFormat sdf = new SimpleDateFormat(format);
	        try{
	             sdf.parse(arg0);
	        } catch (ParseException e) {
	             //intentionally empty
	        }
	    }
	        return true;

	}
	
	

 
   
}
