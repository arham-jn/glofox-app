package com.example.glofox.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.glofox.controller.ClassesController;
import com.example.glofox.dto.ClassesDTO;
import com.example.glofox.dto.ClassesResponseDTO;
import com.example.glofox.service.ClassesService;
import com.example.glofox.util.ResponseEntityBuilder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll methods
class ClassesControllerTest {
	@Mock
	private ResponseEntityBuilder responseEntityBuilder;

	@Mock
	private ClassesService classesService;

	private ClassesController classesController;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		classesController = new ClassesController(responseEntityBuilder, classesService);
	}
	
	@Test
    public void testCreateClasses_withValidationErrors() {
        // Arrange
        ClassesDTO classDto = new ClassesDTO("", LocalDate.now(), LocalDate.now().plusDays(1), 20);  // Invalid class name
        List<String> validationErrors = Arrays.asList("Class name is required");

        when(classesService.validateClass(classDto)).thenReturn(validationErrors);
        when(responseEntityBuilder.errorResponseBuilder(validationErrors))
            .thenReturn(ResponseEntity.badRequest().body(validationErrors));

        // Act
        ResponseEntity<Object> response = classesController.createClasses(classDto);

        // Assert
        assertTrue(response.getBody() instanceof List);
        assertEquals("Class name is required", ((List<?>) response.getBody()).get(0));
    }
	
	@Test
    public void testCreateClasses_withNoValidationErrors() {
        // Arrange
        ClassesDTO classDto = new ClassesDTO("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), 20);
        List<String> validationErrors = Arrays.asList();

        ClassesResponseDTO responseDto = new ClassesResponseDTO("Yoga", LocalDate.now(), LocalDate.now().plusDays(1), 20, 20);

        // Mock the behavior of the classesService.validateClass() and responseEntityBuilder.successResponseBuilder()
        when(classesService.validateClass(classDto)).thenReturn(validationErrors);
        when(classesService.createResponseDto(classDto)).thenReturn(responseDto);
        when(responseEntityBuilder.successResponseBuilder(responseDto)).thenReturn(ResponseEntity.ok(responseDto));

        // Act
        ResponseEntity<Object> response = classesController.createClasses(classDto);

        assertTrue(response.getBody() instanceof ClassesResponseDTO);
    }
}
