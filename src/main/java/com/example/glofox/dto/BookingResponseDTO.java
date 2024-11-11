package com.example.glofox.dto;

import java.time.LocalDate;

public class BookingResponseDTO {

    private String memberName;
    private String className;
    private LocalDate bookingDate;
    private String message;
        
	public BookingResponseDTO(String memberName, String className, LocalDate bookingDate, String message) {
		super();
		this.memberName = memberName;
		this.className = className;
		this.bookingDate = bookingDate;
		this.message = message;
	}
	public String getMemberName() {
		return memberName;
	}
	
	public String getClassName() {
		return className;
	}
	
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	
	public String getMessage() {
		return message;
	}
      
}