package com.example.glofox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.glofox.dto.ClassesDTO;
import com.example.glofox.dto.ClassesResponseDTO;
import com.example.glofox.repository.ClassesRepository;
import com.example.glofox.validation.IClassValidationService;
import com.example.glofox.validation.ClassValidationServiceImpl;

/*
 * This layer handles business logic
 * orchestrates between the repository and validation components.
 */

@Service
public class ClassesServiceImpl implements ClassesService{
	
	private IClassValidationService classValidationServiceObj;
	private ClassesRepository classesRepositoryObj;
	
	public ClassesServiceImpl(IClassValidationService classValidationServiceObj, ClassesRepository classesRepositoryObj) {
		super();
		this.classValidationServiceObj = classValidationServiceObj;
		this.classesRepositoryObj = classesRepositoryObj;
	}

	@Override
	public List<String> validateClass(ClassesDTO classDto) {
		return classValidationServiceObj.validate(classDto);
	}

	@Override
	public void saveClass(ClassesDTO classDto) {
		classesRepositoryObj.save(classDto);
	}

	@Override
	public ClassesResponseDTO createResponseDto(ClassesDTO classDto) {
		return new ClassesResponseDTO(
				classDto.getClassName(), 
				classDto.getStartDate(),
                classDto.getEndDate(),
                classDto.getCapacity(),
                classDto.getCapacity() // Initially all slots are available);
                );
	}

}
