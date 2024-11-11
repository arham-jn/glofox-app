package com.example.glofox.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.example.glofox.dto.BookingDTO;
import com.example.glofox.validation.BookingValidationServiceImpl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll methods
class BookingValidationServiceImplTest {

	private BookingValidationServiceImpl bookingValidationService;

    @BeforeEach
    public void setUp() {
        bookingValidationService = new BookingValidationServiceImpl();
    }

    @Test
    public void testValidate_WhenAllFieldsAreValid_ShouldReturnNoErrors() {
        // Arrange
        BookingDTO validBooking = new BookingDTO("John Doe", "Yoga", LocalDate.now());

        // Act
        List<String> errors = bookingValidationService.validate(validBooking);

        // Assert
        assertTrue(errors.isEmpty(), "There should be no validation errors when all fields are valid.");
    }
    
    @Test
    public void testValidate_WhenMemberNameIsNull_ShouldReturnError() {
        // Arrange
        BookingDTO bookingWithNullMemberName = new BookingDTO(null, "Yoga", LocalDate.now());

        // Act
        List<String> errors = bookingValidationService.validate(bookingWithNullMemberName);

        // Assert
        assertEquals(1, errors.size(), "There should be one validation error.");
        assertTrue(errors.contains("Please enter member name."), "The error message for missing member name should be present.");
    }
    
    @Test
    public void testValidate_WhenBookingDateIsNull_ShouldReturnError() {
        // Arrange
        BookingDTO bookingWithNullDate = new BookingDTO("John Doe", "Yoga", null);

        // Act
        List<String> errors = bookingValidationService.validate(bookingWithNullDate);

        // Assert
        assertEquals(1, errors.size(), "There should be one validation error.");
        assertTrue(errors.contains("Please enter booking date"), "The error message for missing booking date should be present.");
    }

}
