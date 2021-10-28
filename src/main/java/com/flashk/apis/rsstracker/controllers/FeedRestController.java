package com.flashk.apis.rsstracker.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.controllers.model.PagedResponse;
import com.flashk.apis.rsstracker.controllers.model.ValidationGroups.Create;
import com.flashk.apis.rsstracker.services.FeedService;

@RestController
@RequestMapping("/feeds")
public class FeedRestController {

	@Autowired
	private FeedService feedService;
	
	@GetMapping
	public ResponseEntity<PagedResponse<Feed>> listFeeds(Pageable pageable) {
		
		PagedResponse<Feed> feedPage = feedService.listFeeds(pageable);
		
		if(!feedPage.isEmpty()) {
			return new ResponseEntity<PagedResponse<Feed>>(feedPage, HttpStatus.OK);
		} else {
			return new ResponseEntity<PagedResponse<Feed>>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	@PostMapping
	public ResponseEntity<Void> createFeed(@RequestBody @Validated(Create.class) Feed feed, 
															UriComponentsBuilder uriBuilder) {
		
		String feedId = feedService.createFeed(feed);
		
		URI locationUri = uriBuilder.path("/feeds/{feedId}").buildAndExpand(feedId).toUri();
		
		return ResponseEntity.created(locationUri).build();
		
	}
	
	@GetMapping("/{feedId}")
	public ResponseEntity<Feed> getFeed(@PathVariable String feedId) {
		
		Optional<Feed> feed = feedService.getFeed(feedId);
		
		if(feed.isPresent()) {
			return new ResponseEntity<Feed>(feed.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Feed>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@DeleteMapping("/{feedId}")
	public ResponseEntity<Void> deleteFeed(@PathVariable String feedId) {
		
		feedService.deleteFeed(feedId);
		return ResponseEntity.noContent().build();
		
	}
}
