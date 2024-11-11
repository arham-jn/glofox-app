package com.example.glofox.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.example.glofox.dto.ClassesDTO;
import com.example.glofox.validation.ClassValidationServiceImpl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll methods
class ClassValidationServiceImplTest {

	private ClassValidationServiceImpl validationService;

    @BeforeAll
    public void setUp() {
        validationService = new ClassValidationServiceImpl();
    }

    @Test
    public void testValidate_WithEmptyClassName() {
        // Arrange
        ClassesDTO classDto = new ClassesDTO("", LocalDate.now(), LocalDate.now().plusDays(1), 20);

        // Act
        List<String> errors = validationService.validate(classDto);

        // Assert
        assertEquals(1, errors.size());
        assertEquals("Please define the name of the course/class", errors.get(0));
    }
    
    @Test
    public void testValidate_WithNullStartDateAndEndDate() {
        // Arrange
        ClassesDTO classDto = new ClassesDTO("Yoga", null, null, 20);

        // Act
        List<String> errors = validationService.validate(classDto);

        // Assert
        assertEquals(1, errors.size());
        assertEquals("Start date and end date is required.", errors.get(0));
    }
    
    @Test
    public void testValidate_WithInvalidCapacity() {
        // Arrange
        ClassesDTO classDto = new ClassesDTO("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), 0);

        // Act
        List<String> errors = validationService.validate(classDto);

        // Assert
        assertEquals(1, errors.size());
        assertEquals("Capacity must be greater than 0.", errors.get(0));
    }
    
    @Test
    public void testValidate_WithStartDateAfterEndDate() {
        // Arrange
        ClassesDTO classDto = new ClassesDTO("Yoga", LocalDate.now().plusDays(5), LocalDate.now(), 20);

        // Act
        List<String> errors = validationService.validate(classDto);

        // Assert
        assertEquals(1, errors.size());
        assertEquals("Start date cannot be greater than end date", errors.get(0));
    }

    @Test
    public void testValidate_WithValidClassDto() {
        // Arrange
        ClassesDTO classDto = new ClassesDTO("Yoga", LocalDate.now(), LocalDate.now().plusDays(5), 20);

        // Act
        List<String> errors = validationService.validate(classDto);

        // Assert
        assertTrue(errors.isEmpty());
    }

}
