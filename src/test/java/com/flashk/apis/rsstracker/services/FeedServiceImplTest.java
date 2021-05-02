package com.flashk.apis.rsstracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.repositories.FeedRepository;
import com.flashk.apis.rsstracker.repositories.entities.FeedEntity;
import com.flashk.apis.rsstracker.services.mappers.FeedMapper;
import com.flashk.apis.rsstracker.services.mappers.FeedMapperImpl;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
class FeedServiceImplTest {

	private static PodamFactory podamFactory;
	
	@InjectMocks
	private FeedService feedService = new FeedServiceImpl();
	
	@Mock
	private FeedRepository feedRepository;
	
	@Spy
	private FeedMapper feedMapper = new FeedMapperImpl();
	
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
		List<FeedEntity> expected = podamFactory.manufacturePojo(ArrayList.class, FeedEntity.class);
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedRepository).findAll();
		
		// Execute method
		List<Feed> result = feedService.listFeeds();
		
		// Assertions
		Mockito.verify(feedRepository).findAll(); // Verify it executed at least once.
		
		assertNotNull(result);
		assertEquals(expected.size(), result.size());

		// Mapping testing is performed at: FeedMapperTest
	}
	
	@Test
	void testListFeedsEmpty() {
		
		// Prepare POJOs
		List<FeedEntity> expected = new ArrayList<>();
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedRepository).findAll();
		
		// Execute method
		List<Feed> result = feedService.listFeeds();
		
		// Assertions
		Mockito.verify(feedRepository).findAll(); // Verify it executed at least once.
		
		assertNotNull(result);
		assertEquals(expected.size(), result.size());
		assertTrue(result.isEmpty());

	}
	
	@Test
	void testGetFeed() {
		
		// Prepare POJOs
		FeedEntity expected = podamFactory.manufacturePojo(FeedEntity.class);
		Optional<FeedEntity> expectedOptional = Optional.of(expected);
		
		// Prepare mocks
		Mockito.doReturn(expectedOptional).when(feedRepository).findById(any());
		
		// Execute method
		Optional<Feed> result = feedService.getFeed("1234");
		
		// Assertions
		Mockito.verify(feedRepository).findById(any()); // Verify it executed at least once
		
		assertNotNull(result);
		assertTrue(result.isPresent()); // There is data
	}
	
	@Test
	void testGetFeedNotPresent() {
		
		// Prepare POJOs
		Optional<FeedEntity> expectedOptional = Optional.empty();
		
		// Prepare mocks
		Mockito.doReturn(expectedOptional).when(feedRepository).findById(any());
		
		// Execute method
		Optional<Feed> result = feedService.getFeed("1234");
		
		// Assertions
		Mockito.verify(feedRepository).findById(any()); // Verify it executed at least once
		
		assertNotNull(result);
		assertFalse(result.isPresent()); // There is data
	}


}
