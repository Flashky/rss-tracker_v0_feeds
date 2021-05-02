package com.flashk.apis.rsstracker.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
	@SuppressWarnings("unchecked")
	void testListFeeds() {
		
		// Prepare POJOs
		List<FeedEntity> expected = podamFactory.manufacturePojo(ArrayList.class, FeedEntity.class);
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedRepository).findAll();
		
		// Execute method
		List<Feed> result = feedService.listFeeds();
		
		// Assertions
		assertNotNull(result);
		assertEquals(expected.size(), result.size());

		// Mapping testing is performed at: FeedMapperTest
	}


}
