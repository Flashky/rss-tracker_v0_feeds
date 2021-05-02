package com.flashk.apis.rsstracker.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.services.FeedService;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
class FeedRestControllerTest {

	@InjectMocks
	private FeedRestController feedRestController = new FeedRestController();
	
	@Mock
	private FeedService feedService;
	
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
	void testListFeeds() {
		
		// Prepare POJOs
		List<Feed> expected = podamFactory.manufacturePojo(ArrayList.class, Feed.class);
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedService).listFeeds();
		
		// Execute method
		List<Feed> result = feedRestController.listFeeds();
		
		// Assertions
		assertNotNull(result);
		assertEquals(expected.size(), result.size());

	}
	
	@Test
	void testGetFeed() {
		
		// Prepare POJOs
		Feed expected = podamFactory.manufacturePojo(Feed.class);
		Optional<Feed> expectedOptional = Optional.of(expected);
		
		// Prepare mocks
		Mockito.doReturn(expectedOptional).when(feedService).getFeed(any());
		
		// Execute method
		ResponseEntity<Feed> result = feedRestController.getFeed("1234");
		
		// Assertions
		assertNotNull(result);
		assertNotNull(result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());

	}
	
	@Test
	void testGetFeedNotPresent() {
		
		// Prepare POJOs
		Optional<Feed> expectedOptional = Optional.empty();
		
		// Prepare mocks
		Mockito.doReturn(expectedOptional).when(feedService).getFeed(any());
		
		// Execute method
		ResponseEntity<Feed> result = feedRestController.getFeed("1234");
		
		// Assertions
		assertNotNull(result);
		assertNull(result.getBody());
		assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

	}

}
