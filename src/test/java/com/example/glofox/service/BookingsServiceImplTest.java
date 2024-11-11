package com.example.glofox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.glofox.dto.BookingDTO;
import com.example.glofox.dto.BookingResponseDTO;
import com.example.glofox.repository.BookingRepository;
import com.example.glofox.service.BookingService;
import com.example.glofox.service.BookingServiceImpl;
import com.example.glofox.service.ClassesService;
import com.example.glofox.validation.IBookingValidationService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll methods
class BookingsServiceImplTest {

	@Mock
    private IBookingValidationService bookingValidationServiceObj;

    @Mock
    private BookingRepository bookingRepositoryOj;
    
    private BookingService bookingService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingService = new BookingServiceImpl(bookingValidationServiceObj, bookingRepositoryOj);
    }
    
    @Test
    public void testBuildBookingResponse() {
    	BookingDTO bookingDto = new BookingDTO("John Doe", "Yoga", LocalDate.now());
        BookingResponseDTO response = bookingService.buildBookingResponse(bookingDto);

        assertEquals("John Doe", response.getMemberName());
        assertEquals("Yoga", response.getClassName());
        assertEquals("Your booking is confirmed", response.getMessage());
        assertEquals(LocalDate.now(), response.getBookingDate());
    }

    @Test
    public void testValidateBooking_ValidBooking() {
    	BookingDTO bookingDto = new BookingDTO("Jane Doe", "Pilates", LocalDate.now());
        when(bookingValidationServiceObj.validate(any(BookingDTO.class))).thenReturn(new ArrayList<>());

        List<String> validationErrors = bookingService.validateBooking(bookingDto);

        assertTrue(validationErrors.isEmpty());
        verify(bookingValidationServiceObj, times(1)).validate(bookingDto);
    }

    @Test
    public void testValidateBooking_InvalidBooking() {
    	BookingDTO bookingDto = new BookingDTO("Jane Doe", "Pilates", LocalDate.now().minusDays(1));
        List<String> errors = List.of("Booking date cannot be in the past.");
        when(bookingValidationServiceObj.validate(any(BookingDTO.class))).thenReturn(errors);

        List<String> validationErrors = bookingService.validateBooking(bookingDto);

        assertEquals(1, validationErrors.size());
        assertEquals("Booking date cannot be in the past.", validationErrors.get(0));
        verify(bookingValidationServiceObj, times(1)).validate(bookingDto);
    }
    
    @Test
    public void testSaveBooking() {
    	BookingDTO bookingDto = new BookingDTO("Jake Smith", "Cardio", LocalDate.now());
        bookingService.saveBooking(bookingDto);

        verify(bookingRepositoryOj, times(1)).save(bookingDto);
    }

}
