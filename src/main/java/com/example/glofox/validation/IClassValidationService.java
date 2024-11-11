package com.example.glofox.validation;

import java.util.List;

import com.example.glofox.dto.ClassesDTO;

/*
 * To comply with the Open/Closed Principle
 * create an interface that different validations can implement
 * allowing us to add more validators later.
 */
public interface IClassValidationService {
	List<String> validate(ClassesDTO dto);
}
