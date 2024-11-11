package com.example.glofox.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.glofox.dto.BookingDTO;
import com.example.glofox.dto.BookingResponseDTO;
import com.example.glofox.repository.BookingRepository;
import com.example.glofox.repository.ClassesRepository;
import com.example.glofox.validation.BookingValidationServiceImpl;
import com.example.glofox.validation.IBookingValidationService;

@Service
public class BookingServiceImpl implements BookingService {
	
	 // In-memory storage for bookings
	List<BookingDTO> bookings = new ArrayList<BookingDTO>();

	private IBookingValidationService bookingValidationServiceObj;
	private BookingRepository bookingRepositoryOj;

	
	public BookingServiceImpl(IBookingValidationService bookingValidationServiceObj, BookingRepository bookingRepositoryOj) {
		super();
		this.bookingValidationServiceObj = bookingValidationServiceObj;
		this.bookingRepositoryOj = bookingRepositoryOj;
	}


	@Override
	public BookingResponseDTO buildBookingResponse(BookingDTO bookingDto) {
		return new BookingResponseDTO(
				bookingDto.getMemberName(),
				bookingDto.getClassName(),
				bookingDto.getBookingDate(),
				"Your booking is confirmed");
	}


	@Override
	public List<String> validateBooking(BookingDTO bookingDto) {
		return bookingValidationServiceObj.validate(bookingDto);
	}


	@Override
	public void saveBooking(BookingDTO bookingDto) {
		bookingRepositoryOj.save(bookingDto);
	}

}
