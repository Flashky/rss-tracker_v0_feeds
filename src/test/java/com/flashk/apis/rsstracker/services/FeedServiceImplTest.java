package com.flashk.apis.rsstracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.flashk.apis.rsstracker.controllers.exceptions.RssNotFoundException;
import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.controllers.model.PagedResponse;
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
		Pageable pageable = podamFactory.manufacturePojo(Pageable.class);
		List<FeedEntity> feedEntities = podamFactory.manufacturePojo(ArrayList.class, FeedEntity.class);
		Page<FeedEntity> expected = new PageImpl<>(feedEntities);
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedRepository).findAll(pageable);
		
		// Execute method
		PagedResponse<Feed> result = feedService.listFeeds(pageable);
		
		// Assertions
		Mockito.verify(feedRepository).findAll(pageable); // Verify it executed at least once.
		
		assertNotNull(result);
		assertNotNull(result.getData());
		assertEquals(expected.getSize(), result.getData().size());

		// Mapping testing is performed at: FeedMapperTest
	}
	
	@Test
	void testListFeedsEmpty() {
		
		// Prepare POJOs
		Pageable pageable = podamFactory.manufacturePojo(Pageable.class);
		Page<FeedEntity> expected = Page.empty();
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedRepository).findAll(pageable);
		
		// Execute method
		PagedResponse<Feed> result = feedService.listFeeds(pageable);
		
		// Assertions
		Mockito.verify(feedRepository).findAll(pageable); // Verify it executed at least once.
		
		assertNotNull(result);
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
	
	@Test
	void testDeleteFeed() {
		
		// Prepare mocks
		Mockito.doReturn(true).when(feedRepository).existsById(any());
		Mockito.doNothing().when(feedRepository).deleteById(any());
		
		// Execute method
		feedService.deleteFeed("1234");
		
		// Assertions
		Mockito.verify(feedRepository).deleteById(any());
		
	}
	
	@Test
	void testDeleteFeedNotPresent() {
		
		// Prepare mocks
		Mockito.doReturn(false).when(feedRepository).existsById(any());

		// Execute method + Assertion
		Assertions.assertThrows(RssNotFoundException.class, () -> feedService.deleteFeed("1234"));
		
	}


}
