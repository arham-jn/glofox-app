package com.example.glofox.validation;

import java.util.List;

import com.example.glofox.dto.BookingDTO;

public interface IBookingValidationService {
	List<String> validate(BookingDTO dto);
}