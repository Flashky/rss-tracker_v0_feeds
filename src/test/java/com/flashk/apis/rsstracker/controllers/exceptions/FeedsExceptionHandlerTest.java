package com.flashk.apis.rsstracker.controllers.exceptions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.WebRequest;

import com.flashk.apis.rsstracker.controllers.model.ErrorResponse;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

class FeedsExceptionHandlerTest {

	private FeedsExceptionHandler feedsExceptionHandler = new FeedsExceptionHandler();
	
	private static PodamFactory podamFactory;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	    podamFactory = new PodamFactoryImpl();
	    podamFactory.getStrategy().setDefaultNumberOfCollectionElements(2);
	    
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testHandleHttpMessageNotReadableHttpMessageNotReadableExceptionHttpHeadersHttpStatusWebRequest() {
		
		// Prepare POJOs
		HttpMessageNotReadableException ex = podamFactory.manufacturePojo(HttpMessageNotReadableException.class);
		HttpHeaders headers = HttpHeaders.EMPTY;
		HttpStatus status = podamFactory.manufacturePojo(HttpStatus.class);
		WebRequest request = podamFactory.manufacturePojo(WebRequest.class);
		
		// Execute method
		ResponseEntity<Object> response = feedsExceptionHandler.handleHttpMessageNotReadable(ex, headers, status, request);
		
		// Assertions
		assertNotNull(response);
		
		assertEquals(MediaType.APPLICATION_PROBLEM_JSON, response.getHeaders().getContentType());
		assertTrue(response.getBody() instanceof ErrorResponse);
		
	}

	@Test
	void testHandleInvalidRssException() {

		// Prepare POJOs
		InvalidRssException ex = podamFactory.manufacturePojo(InvalidRssException.class);
				
		// Execute method
		ResponseEntity<Object> response = feedsExceptionHandler.handleInvalidRssException(ex);
		
		// Assertions
		assertNotNull(response);
		
		assertTrue(response.getBody() instanceof ErrorResponse);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
				
	}

	@Test
	void testHandleRssNotFoundException() {
		
		// Prepare POJOs
		RssNotFoundException ex = podamFactory.manufacturePojo(RssNotFoundException.class);
				
		// Execute method
		ResponseEntity<Object> response = feedsExceptionHandler.handleRssNotFoundException(ex);
		
		// Assertions
		assertNotNull(response);
		
		assertTrue(response.getBody() instanceof ErrorResponse);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
	}

	@Test
	void testHandleInvalidTechnicalException() {
		
		// Prepare POJOs
		TechnicalException ex = podamFactory.manufacturePojo(TechnicalException.class);
		
		// Execute method
		ResponseEntity<Object> response = feedsExceptionHandler.handleInvalidTechnicalException(ex);
		
		// Assertions
		assertNotNull(response);
		
		assertTrue(response.getBody() instanceof ErrorResponse);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
	}

}
