package com.flashk.apis.rsstracker.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.services.FeedService;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
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
	@SuppressWarnings("unchecked")
	void testListFeeds() {
		
		// Prepare POJOs
		List<Feed> expected = podamFactory.manufacturePojo(ArrayList.class, Feed.class);
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedService).listFeeds();
		
		// Execute method
		List<Feed> result = feedService.listFeeds();
		
		// Assertions
		assertNotNull(result);
		assertEquals(expected.size(), result.size());

	}

}
