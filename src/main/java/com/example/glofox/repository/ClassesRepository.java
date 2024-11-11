package com.example.glofox.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.glofox.dto.ClassesDTO;

/*
 * 
 * decouple the service layer from the data layer
 * allowing us to swap implementations easily.
 */

public interface ClassesRepository {
	void save(ClassesDTO classesDto);
	Optional<ClassesDTO> findByNameAndDate(String name, LocalDate date);
	List<ClassesDTO> findAll();
}
