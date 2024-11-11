package com.example.glofox.dto;

import java.time.LocalDate;

/*
 * Encapsulates the data we want to send back to the client after processing, 
 * which might differ from the request data.
 * response can evolve without modifying the request format
 */

public class ClassesResponseDTO {
	String className;
	private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;
    private int availableSlots;
    
    
	public ClassesResponseDTO(String className, LocalDate startDate, LocalDate endDate, int capacity, int availableSlots) {
		super();
		this.className = className;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
		this.availableSlots = availableSlots;
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
	
	public int getAvailableSlots() {
		return availableSlots;
	}
 
}
