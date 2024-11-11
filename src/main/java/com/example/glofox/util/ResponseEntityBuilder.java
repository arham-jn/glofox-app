package com.example.glofox.util;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.example.glofox.dto.ClassesResponseDTO;
import com.example.glofox.dto.ErrorResponseDTO;

/*
 * The builder helps centralize response handling
 * making future changes to response structure easier.
 */

@Component
public class ResponseEntityBuilder {
	
	/*
	 * ResponseEntity<Object> is flexible and can hold any type of object
	 * including ClassesResponseDTO and List<String>
     * Builds a success response with a specified HTTP status.
     * @param responseDto The object to be returned in the response body.
     * @param status The HTTP status code for the success response.
     * @return A ResponseEntity containing the success response.
     */

	public ResponseEntity<Object> successResponseBuilder (Object responseDto, HttpStatus status) {
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}
	
	/**
     * Builds a success response with the default CREATED status.
     * @param responseDto The object to be returned in the response body.
     * @return A ResponseEntity containing the success response.
     */
    public ResponseEntity<Object> successResponseBuilder(Object responseDto) {
        return successResponseBuilder(responseDto, HttpStatus.CREATED);
    }
	
    /**
     * Builds an error response with a list of error details.
     * @param errors A list of error messages to be returned in the response body.
     * @return A ResponseEntity containing the error response.
     */
    public ResponseEntity<Object> errorResponseBuilder(List<String> errors) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Validation failed", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }   
    
}
