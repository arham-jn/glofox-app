package com.example.glofox.dto;

import java.time.LocalDate;

public class BookingDTO {
	
	private String memberName;
	private String className;
	private LocalDate bookingDate;
	
	public BookingDTO(String memberName, String className, LocalDate bookingDate) {
		super();
		this.memberName = memberName;
		this.className = className;
		this.bookingDate = bookingDate;
	}

	public String getClassName() {
		return className;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
}
