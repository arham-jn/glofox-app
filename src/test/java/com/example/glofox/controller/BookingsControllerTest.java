package com.example.glofox.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.glofox.controller.BookingsController;
import com.example.glofox.dto.BookingDTO;
import com.example.glofox.dto.BookingResponseDTO;
import com.example.glofox.service.BookingService;
import com.example.glofox.util.ResponseEntityBuilder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll methods
class BookingsControllerTest {

	@Mock
	private BookingService bookingService;  // Mock the service layer

	@Mock
	private ResponseEntityBuilder responseEntityBuilder;  // Mock the utility for response construction

	@InjectMocks
	private BookingsController bookingsController;  // The controller to be tested

    private BookingDTO validBookingDto;
    private BookingResponseDTO bookingResponseDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validBookingDto = new BookingDTO("John Doe", "Yoga", LocalDate.now());
        bookingResponseDto = new BookingResponseDTO("John Doe", "Yoga", LocalDate.now(), "Your booking is confirmed");
    }

    
    @Test
    public void testCreateBooking_WhenValidationFails_ShouldReturnErrorResponse() {
        // Arrange
        BookingDTO invalidBookingDto = new BookingDTO(null, "Yoga", LocalDate.now());  // Member name is null
        List<String> validationErrors = List.of("Please enter member name.");
        
        // Mock the service call to return validation errors
        when(bookingService.validateBooking(invalidBookingDto)).thenReturn(validationErrors);
        
        // Mock the responseEntityBuilder call to return a BAD_REQUEST response with the validation errors
        when(responseEntityBuilder.errorResponseBuilder(validationErrors))
                .thenReturn(new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST));

        // Act: Call the controller method
        ResponseEntity<Object> response = bookingsController.createBooking(invalidBookingDto);

        // Assert: Check that the response has a BAD_REQUEST status and contains the validation errors
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(validationErrors, response.getBody());
        
        // Verify that the validation method was called
        verify(bookingService).validateBooking(invalidBookingDto);
        
        // Verify that errorResponseBuilder was called
        verify(responseEntityBuilder).errorResponseBuilder(validationErrors);
        
        // Verify that saveBooking and buildBookingResponse were never called
        verify(bookingService, never()).saveBooking(invalidBookingDto);
        verify(bookingService, never()).buildBookingResponse(invalidBookingDto);
    }

    @Test
    public void testCreateBooking_WhenValidationSucceeds_ShouldReturnSuccessResponse() {
        // Arrange
        when(bookingService.validateBooking(validBookingDto)).thenReturn(Collections.emptyList());
        when(responseEntityBuilder.successResponseBuilder(bookingResponseDto))
                .thenReturn(new ResponseEntity<>(bookingResponseDto, HttpStatus.CREATED));
        when(bookingService.buildBookingResponse(validBookingDto)).thenReturn(bookingResponseDto);

        // Act
        ResponseEntity<Object> response = bookingsController.createBooking(validBookingDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bookingResponseDto, response.getBody());
        verify(bookingService).validateBooking(validBookingDto);
        verify(bookingService).saveBooking(validBookingDto);
        verify(bookingService).buildBookingResponse(validBookingDto);
        verify(responseEntityBuilder).successResponseBuilder(bookingResponseDto);
    }

}
