package com.example.glofox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.glofox.dto.ClassesDTO;
import com.example.glofox.dto.ClassesResponseDTO;
import com.example.glofox.repository.ClassesRepository;
import com.example.glofox.service.ClassesService;
import com.example.glofox.service.ClassesServiceImpl;
import com.example.glofox.validation.IClassValidationService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll methods
public class ClassesServiceImplTest {
	@Mock
    private IClassValidationService classValidationService;

    @Mock
    private ClassesRepository classesRepository;

    private ClassesService classesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        classesService = new ClassesServiceImpl(classValidationService, classesRepository);
    }

    //This test checks if the validateClass() method correctly returns validation errors when the class name is empty.
    @Test
    public void testValidateAndSaveClass_withValidationErrors() {
    	// Arrange
        ClassesDTO classDto = new ClassesDTO("", LocalDate.now(), LocalDate.now().plusDays(1), 20);  // Invalid class name

        when(classValidationService.validate(any(ClassesDTO.class))).thenReturn(Arrays.asList("Class name is required"));

        // Act
        List<String> validationErrors = classesService.validateClass(classDto);

        // Assert
        assertFalse(validationErrors.isEmpty());
        assertEquals("Class name is required", validationErrors.get(0));
        verify(classesRepository, never()).save(any(ClassesDTO.class));  // Save should not be called
    }
    	
    //This test checks if validateClass() returns an empty list when no validation errors are present (valid DTO).
    @Test
    public void testValidateAndSaveClass_withoutValidationErrors() {
    	// Arrange
        ClassesDTO classDto = new ClassesDTO("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), 20);  // Valid class name

        when(classValidationService.validate(classDto)).thenReturn(Arrays.asList());

        // Act
        List<String> validationErrors = classesService.validateClass(classDto);

        // Assert
        assertTrue(validationErrors.isEmpty());
        verify(classesRepository, never()).save(classDto);  // Save should not be called yet
    }
    
    //This test ensures that the saveClass() method saves the class using the repository when it is called.
    @Test
    public void testSaveClass() {
        // Arrange
        ClassesDTO classDto = new ClassesDTO("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), 20);  // Valid class

        // Act
        classesService.saveClass(classDto);

        // Assert
        verify(classesRepository, times(1)).save(classDto);  // Ensure save method is called once
    }

    //This test combines both methods. First, we validate the class, and then, 
    //if validation passes, we test saving it in the repository.
    @Test
    public void testValidateClass_and_SaveClass_successful() {
        // Arrange
        ClassesDTO classDto = new ClassesDTO("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), 20);  // Valid class

        when(classValidationService.validate(classDto)).thenReturn(Arrays.asList());  // No errors

        // Act
        List<String> validationErrors = classesService.validateClass(classDto);

        // Assert
        assertTrue(validationErrors.isEmpty());  // No validation errors

        // Now call saveClass() and ensure it calls save on the repository
        classesService.saveClass(classDto);
        verify(classesRepository, times(1)).save(classDto);  // Ensure save method is called
    }
}
