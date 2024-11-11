package com.example.glofox.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.glofox.dto.ClassesDTO;
import com.example.glofox.dto.ClassesResponseDTO;
import com.example.glofox.service.ClassesService;
import com.example.glofox.util.ResponseEntityBuilder;

@RestController
@RequestMapping("/classes")
public class ClassesController {
	
	private final ResponseEntityBuilder responseEntityBuilderObj;
	private final ClassesService classesServiceObj;
	
	public ClassesController(ResponseEntityBuilder responseEntityBuilderObj, ClassesService classesServiceObj) {
		super();
		this.responseEntityBuilderObj = responseEntityBuilderObj;
		this.classesServiceObj = classesServiceObj;
	}

	@PostMapping
	public ResponseEntity<Object> createClasses(@RequestBody ClassesDTO classDto) {
		
		/*
		 * creating a ClassValidationService to handle validation
		 * This will ensure controller remains focused on handling requests and responses
		 */
		
		List<String> validationErrors = classesServiceObj.validateClass(classDto);
		//if error list is not null
		//build error response with all the errors 
		if(!validationErrors.isEmpty()) {
			return responseEntityBuilderObj.errorResponseBuilder(validationErrors);
		}
		// If validation is successful, save the class
        classesServiceObj.saveClass(classDto);
        
		ClassesResponseDTO responseDtoObj = classesServiceObj.createResponseDto(classDto);
		return responseEntityBuilderObj.successResponseBuilder(responseDtoObj);
	}
}
