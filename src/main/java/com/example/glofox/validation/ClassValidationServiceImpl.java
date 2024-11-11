package com.example.glofox.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.glofox.dto.ClassesDTO;

/*
 * Separating the logic into a dedicated validator class implementing ClassValidator
 * making the validation layer open for extension but closed for modification.
 */

@Component
public class ClassValidationServiceImpl implements IClassValidationService  {	

	@Override
	public List<String> validate(ClassesDTO classDto) {
		List<String> errors = new ArrayList<String>();	
		
		if(classDto.getClassName() == null || classDto.getClassName().isEmpty()) {
			errors.add("Please define the name of the course/class");
		}
		if(classDto.getStartDate()==null || classDto.getEndDate()==null) {
			errors.add("Start date and end date is required.");
		}
		if(classDto.getCapacity() <=0) {
			errors.add("Capacity must be greater than 0.");
		}
		if(classDto.getStartDate() !=null && classDto.getEndDate()!=null 
				&& classDto.getStartDate().isAfter(classDto.getEndDate())) {
			errors.add("Start date cannot be greater than end date");
		}
		
		return errors;
	}

}
