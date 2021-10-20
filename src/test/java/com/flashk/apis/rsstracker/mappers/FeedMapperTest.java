package com.flashk.apis.rsstracker.mappers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.repositories.entities.FeedEntity;
import com.flashk.apis.rsstracker.services.mappers.FeedMapper;
import com.flashk.apis.rsstracker.services.mappers.FeedMapperImpl;

import uk.co.jemos.podam.api.PodamFactoryImpl;

class FeedMapperTest {

	private FeedMapper feedMapper = new FeedMapperImpl();
	private static PodamFactoryImpl podamFactory;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		// Podam (POJOs) configuration
	    podamFactory = new PodamFactoryImpl();
	    podamFactory.getStrategy().setDefaultNumberOfCollectionElements(2);
	    
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testMapFeedEntity() {
		
		// Prepare POJOs
		FeedEntity expected = podamFactory.manufacturePojo(FeedEntity.class);
		
		// Execute test
		Feed result = feedMapper.map(expected);
		
		// Assertions
		testMapping(expected, result);
		
	}

	@Test
	void testMapFeed() {
		// Prepare POJOs
		Feed expected = podamFactory.manufacturePojo(Feed.class);
		
		// Execute test
		FeedEntity result = feedMapper.map(expected);
		
		// Assertions
		testMapping(expected, result);
	}
	
	@Test
	void testMapFeedDefaultIsEnabled() {
		// Prepare POJOs
		Feed expected = podamFactory.manufacturePojo(Feed.class);
		expected.setIsEnabled(null);
		
		// Execute test
		FeedEntity result = feedMapper.map(expected);
		
		// Assertions
		assertTrue(result.getIsEnabled());
	}
	
	private void testMapping(Feed expected, FeedEntity result) {
		
		assertNotNull(result);
		
		assertEquals(expected.getId(), result.getId());
		assertEquals(expected.getCreatedDate().getTime(), result.getCreatedDate().getTime());
		assertEquals(expected.getLastModifiedDate().getTime(), result.getLastModifiedDate().getTime());
		assertEquals(expected.getDescription(), result.getDescription());
		assertEquals(expected.getIsEnabled(), result.getIsEnabled());
		assertEquals(expected.getUrl(), result.getUrl());
	}

	private void testMapping(FeedEntity expected, Feed result) {
		
		assertNotNull(result);
		
		assertEquals(expected.getId(), result.getId());
		assertEquals(expected.getCreatedDate().getTime(), result.getCreatedDate().getTime());
		assertEquals(expected.getLastModifiedDate().getTime(), result.getLastModifiedDate().getTime());
		assertEquals(expected.getDescription(), result.getDescription());
		assertEquals(expected.getIsEnabled(), result.getIsEnabled());
		assertEquals(expected.getUrl(), result.getUrl());
	}

	
	@Test
	@SuppressWarnings("unchecked")
	void testMapListOfFeedEntity() {
		// Prepare POJOs
		List<FeedEntity> expected = podamFactory.manufacturePojo(ArrayList.class, FeedEntity.class);
				
		// Execute test
		List<Feed> result = feedMapper.map(expected);
		
		// Assertions
		assertNotNull(result);
		assertEquals(expected.size(), result.size());
		
		for(int i = 0; i < result.size();i++) {
			testMapping(expected.get(i), result.get(i));
		}
				
	}

}
