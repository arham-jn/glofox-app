package com.example.glofox.service;

import java.util.List;
import com.example.glofox.dto.BookingDTO;
import com.example.glofox.dto.BookingResponseDTO;

public interface BookingService {
	List<String> validateBooking(BookingDTO bookingDto);
	void saveBooking(BookingDTO bookingDto);
	BookingResponseDTO buildBookingResponse(BookingDTO bookingDto);
}
