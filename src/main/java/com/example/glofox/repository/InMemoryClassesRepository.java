package com.example.glofox.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;
import com.example.glofox.dto.ClassesDTO;

/*
 * An in-memory repository implements the ClassRepository interface, 
 * adhering to the Dependency Inversion Principle.
 */

@Repository
public class InMemoryClassesRepository implements ClassesRepository {
	
	//Use CopyOnWriteArrayList instead of ArrayList if the 
	//repository might be accessed concurrently, ensuring thread safety.
		
	private List<ClassesDTO> classes = new CopyOnWriteArrayList<ClassesDTO>();

	@Override
	public void save(ClassesDTO classesDto) {
		classes.add(classesDto);
	}

	@Override
	public List<ClassesDTO> findAll() {
		return new ArrayList<>(classes);
	}

	@Override
	public Optional<ClassesDTO> findByNameAndDate(String name, LocalDate date) {
		return classes.stream()
                .filter(c -> c.getClassName().equals(name) && !date.isBefore(c.getStartDate()) && !date.isAfter(c.getEndDate()))
                .findFirst();
	}

}
