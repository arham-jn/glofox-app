package com.example.glofox.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.glofox.dto.BookingDTO;

@Repository
public class InMemoryBookingRepository implements BookingRepository {
	
	private final List<BookingDTO> bookingList = new ArrayList<>();

	@Override
	public void save(BookingDTO bookingDto) {
		bookingList.add(bookingDto);
	}

	@Override
	public List<BookingDTO> findAllBookings() {
		return bookingList;
	}

	@Override
	public List<BookingDTO> findByDate(LocalDate date) {
		return bookingList.stream()
                .filter(b -> b.getBookingDate().equals(date))
                .collect(Collectors.toList());
	}

}
