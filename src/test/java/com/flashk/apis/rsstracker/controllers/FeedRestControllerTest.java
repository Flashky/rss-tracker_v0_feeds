package com.flashk.apis.rsstracker.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.flashk.apis.rsstracker.controllers.exceptions.RssNotFoundException;
import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.controllers.model.Link;
import com.flashk.apis.rsstracker.controllers.model.PagedResponse;
import com.flashk.apis.rsstracker.services.FeedService;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
class FeedRestControllerTest {

	private final static String SAMPLE_BASE_URI = "http://base-uri";
	
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
		Pageable pageable = podamFactory.manufacturePojo(Pageable.class);
		List<Feed> feeds = podamFactory.manufacturePojo(ArrayList.class, Feed.class);
		PagedResponse<Feed> expected = PagedResponse.<Feed>builder().data(feeds).page(null).build();
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedService).listFeeds(pageable);
		
		// Execute method
		ResponseEntity<PagedResponse<Feed>> response = feedRestController.listFeeds(pageable);
		
		
		// Assertions - Verifications
		Mockito.verify(feedService).listFeeds(pageable); // Verify service is called at least once.
		
		// Assertions - Response
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		// Assertions - Body
		PagedResponse<Feed> result = response.getBody();
		
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(expected.getData().size(), result.getData().size());

	}
	
	@Test
	void testListFeedsEmpty() {
		
		// Prepare POJOs
		Pageable pageable = podamFactory.manufacturePojo(Pageable.class);
		PagedResponse<Feed> expected = PagedResponse.<Feed>builder().data(new ArrayList<>()).build();
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedService).listFeeds(pageable);
		
		// Execute method
		ResponseEntity<PagedResponse<Feed>> response = feedRestController.listFeeds(pageable);
		
		// Assertions - Verifications
		Mockito.verify(feedService).listFeeds(pageable); // Verify service is called at least once.
		
		// Assertions - Response
		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
		// Assertions - Body
		PagedResponse<Feed> result = response.getBody();
		assertNull(result);

	}
	
	@Test
	void testCreateFeed() {
		
		// Prepare POJOs
		Feed feed = new Feed();
		feed.setSourceLink(podamFactory.manufacturePojo(Link.class));
		Feed expected = podamFactory.manufacturePojo(Feed.class);
		String expectedLocationHeader = SAMPLE_BASE_URI + "/feeds/"+expected.getId();
		
		// Prepare mocks
		Mockito.doReturn(expected).when(feedService).createFeed(any());
		
		// Execute method
		ResponseEntity<Feed> result = feedRestController.createFeed(feed, UriComponentsBuilder.fromHttpUrl(SAMPLE_BASE_URI));
		
		// Assertions - Verifications
		Mockito.verify(feedService).createFeed(any());
		
		// Assertions - Response
		assertNotNull(result);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		
		// Assertions - Body
		assertNotNull(result.getBody());
		
		// Assertions - Headers
		assertNotNull(result.getHeaders());
		assertNotNull(result.getHeaders().getLocation());
		assertEquals(expectedLocationHeader, result.getHeaders().getLocation().toString());
		
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
		
		// Assertions - Verifications
		Mockito.verify(feedService).getFeed(any()); // Verify service is called at least once.
		
		// Assertions - Response
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		
		// Assertions - Body
		assertNotNull(result.getBody());
		
	}
	
	@Test
	void testGetFeedNotPresent() {
		
		// Prepare POJOs
		Optional<Feed> expectedOptional = Optional.empty();
		
		// Prepare mocks
		Mockito.doReturn(expectedOptional).when(feedService).getFeed(any());
		
		// Execute method
		ResponseEntity<Feed> result = feedRestController.getFeed("1234");
		
		// Assertions - Verifications
		Mockito.verify(feedService).getFeed(any()); // Verify service is called at least once.
		
		// Assertions - Response
		assertNotNull(result);
		assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
		
		// Assertions - Body
		assertNull(result.getBody());

	}
	
	@Test
	void testDeleteFeed() {
		
		// Prepare mocks
		Mockito.doNothing().when(feedService).deleteFeed(any());
		
		// Execute method
		ResponseEntity<Void> result = feedRestController.deleteFeed("1234");

		// Assertions - Verifications
		Mockito.verify(feedService).deleteFeed(any()); // Verify service is called at least once.
				
		// Assertions - Response
		assertNotNull(result);
		assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
		
		// Assertions - Body
		assertNull(result.getBody());
	}
	
	@Test
	void testDeleteFeedNotPresent() {
		
		// Prepare mocks
		Mockito.doThrow(new RssNotFoundException()).when(feedService).deleteFeed(any());
		
		// Execute method
		Assertions.assertThrows(RssNotFoundException.class, () -> feedRestController.deleteFeed("1234"));
		
	}

}
