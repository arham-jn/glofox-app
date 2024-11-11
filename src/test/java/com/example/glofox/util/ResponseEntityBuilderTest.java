package com.example.glofox.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.glofox.dto.ClassesResponseDTO;
import com.example.glofox.dto.ErrorResponseDTO;
import com.example.glofox.util.ResponseEntityBuilder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll methods
class ResponseEntityBuilderTest {

	private ResponseEntityBuilder responseEntityBuilder;

    @BeforeAll
    public void setUp() {
        responseEntityBuilder = new ResponseEntityBuilder();
    }

    //when called without a status, returns a response with HttpStatus.CREATED
    @Test
    public void testSuccessResponseBuilder_WithDefaultStatus() {
        // Arrange
        ClassesResponseDTO responseDto = new ClassesResponseDTO("Yoga", LocalDate.now(), LocalDate.now().plusDays(5), 20, 20);

        // Act
        ResponseEntity<Object> responseEntity = responseEntityBuilder.successResponseBuilder(responseDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }
    
    //returns a BAD_REQUEST status and includes the correct error messages in the body.
    @Test
    public void testErrorResponseBuilder() {
        // Arrange
        List<String> errors = Arrays.asList("Validation failed", "Capacity must be greater than 0.");

        // Act
        ResponseEntity<Object> responseEntity = responseEntityBuilder.errorResponseBuilder(errors);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponseDTO errorResponse = (ErrorResponseDTO) responseEntity.getBody();
        assertEquals("Validation failed", errorResponse.getMessage());
        assertEquals(errors, errorResponse.getDetails());
    }

}
