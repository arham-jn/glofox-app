package com.example.glofox.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.example.glofox.dto.BookingDTO;
import com.example.glofox.repository.InMemoryBookingRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll methods
class InMemoryBookingsRepositoryTest {

	private InMemoryBookingRepository bookingRepository;

    @BeforeEach
    public void setUp() {
        bookingRepository = new InMemoryBookingRepository();
    }

    @Test
    public void testSaveBooking() {
        // Arrange
        BookingDTO bookingDto = new BookingDTO("John Doe", "Yoga", LocalDate.of(2024, 5, 20));

        // Act
        bookingRepository.save(bookingDto);
        List<BookingDTO> allBookings = bookingRepository.findAllBookings();

        // Assert
        assertEquals(1, allBookings.size(), "There should be exactly one booking saved.");
        assertEquals("John Doe", allBookings.get(0).getMemberName(), "Member name should match.");
        assertEquals("Yoga", allBookings.get(0).getClassName(), "Class name should match.");
        assertEquals(LocalDate.of(2024, 5, 20), allBookings.get(0).getBookingDate(), "Booking date should match.");
    }

    @Test
    public void testFindAllBookings() {
        // Arrange
        BookingDTO booking1 = new BookingDTO("John Doe", "Yoga", LocalDate.of(2024, 5, 20));
        BookingDTO booking2 = new BookingDTO("Jane Smith", "Pilates", LocalDate.of(2024, 5, 21));
        bookingRepository.save(booking1);
        bookingRepository.save(booking2);

        // Act
        List<BookingDTO> allBookings = bookingRepository.findAllBookings();

        // Assert
        assertEquals(2, allBookings.size(), "There should be two bookings saved.");
        assertTrue(allBookings.contains(booking1), "Booking1 should be present in the repository.");
        assertTrue(allBookings.contains(booking2), "Booking2 should be present in the repository.");
    }
    
    
    @Test
    public void testFindByDate_WithNoMatchingBookings() {
        // Arrange
        LocalDate date = LocalDate.of(2024, 5, 20);
        BookingDTO booking1 = new BookingDTO("John Doe", "Yoga", LocalDate.of(2024, 5, 19));
        BookingDTO booking2 = new BookingDTO("Jane Smith", "Pilates", LocalDate.of(2024, 5, 21));

        bookingRepository.save(booking1);
        bookingRepository.save(booking2);

        // Act
        List<BookingDTO> bookingsOnDate = bookingRepository.findByDate(date);

        // Assert
        assertTrue(bookingsOnDate.isEmpty(), "There should be no bookings on the specified date.");
    }
}
