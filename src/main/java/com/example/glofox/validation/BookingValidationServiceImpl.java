package com.example.glofox.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.glofox.dto.BookingDTO;

@Component
public class BookingValidationServiceImpl implements IBookingValidationService {
	
	List<String> validationErrors = new ArrayList<String>();
	@Override
	public List<String> validate(BookingDTO bookingDto) {
		if(bookingDto.getMemberName()==null || bookingDto.getMemberName().isEmpty())
			validationErrors.add("Please enter member name.");
		if(bookingDto.getBookingDate()==null)
			validationErrors.add("Please enter booking date");
		
		return validationErrors;
	}

}