package com.example.glofox.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.glofox.dto.BookingDTO;

public interface BookingRepository {
	void save(BookingDTO bookingDto);
	List<BookingDTO> findAllBookings();
	List<BookingDTO> findByDate(LocalDate date);
}
