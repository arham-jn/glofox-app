package com.example.glofox.service;

import java.util.List;

import com.example.glofox.dto.ClassesDTO;
import com.example.glofox.dto.ClassesResponseDTO;

public interface ClassesService {
	List<String> validateClass(ClassesDTO classDto);
	void saveClass(ClassesDTO classDto);	
    ClassesResponseDTO createResponseDto(ClassesDTO classDto);
}
	