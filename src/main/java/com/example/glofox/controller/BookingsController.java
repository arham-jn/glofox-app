package com.example.glofox.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.glofox.dto.BookingDTO;
import com.example.glofox.dto.BookingResponseDTO;
import com.example.glofox.dto.ClassesResponseDTO;
import com.example.glofox.service.BookingService;
import com.example.glofox.service.BookingServiceImpl;
import com.example.glofox.util.ResponseEntityBuilder;

/*
 * abstracting Classes and Bookings Controller
 * Class should have a Single Responsibility
 */
@RestController
@RequestMapping("/bookings")
public class BookingsController {
	private BookingService bookingServiceObj;
	private ResponseEntityBuilder responseEntityObj;
	
	/*
	 * use Constructor injection for testability
	 */
	public BookingsController(BookingService bookingServiceObj, ResponseEntityBuilder responseEntityObj) {
		super();
		this.bookingServiceObj = bookingServiceObj;
		this.responseEntityObj = responseEntityObj;
	}
	
	@PostMapping
	public ResponseEntity<Object> createBooking(BookingDTO bookingDto) {
		//Moving validation logic to the service layer
		List<String> validationErrors = bookingServiceObj.validateBooking(bookingDto);
		
		if(!validationErrors.isEmpty()) {
			return responseEntityObj.errorResponseBuilder(validationErrors);
		}
		bookingServiceObj.saveBooking(bookingDto);
		BookingResponseDTO responseDtoObj = bookingServiceObj.buildBookingResponse(bookingDto);
		return responseEntityObj.successResponseBuilder(responseDtoObj);
	}
	
}
