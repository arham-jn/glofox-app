package com.example.glofox.dto;

import java.time.LocalDate;

/*
 * Captures the data needed for creating or updating a class
 * Responsible For carrying data between layers
 * separating from ResponseDTO
 * Easier to Modify Independently with ResponseDTO
 * Avoids Leaking Internal Details
 */

public class ClassesDTO {
	
	private String className;
	private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;
    
    //getters and setters
    
    public ClassesDTO(String className, LocalDate startDate, LocalDate endDate,int capacity) {
		super();
		this.className = className;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
	}

	public String getClassName() {
		return className;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public int getCapacity() {
		return capacity;
	}

}