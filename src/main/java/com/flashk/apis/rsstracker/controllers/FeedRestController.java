package com.flashk.apis.rsstracker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.services.FeedService;

@RestController
@RequestMapping("/feeds")
public class FeedRestController {

	@Autowired
	private FeedService feedService;
	
	@GetMapping
	public ResponseEntity<List<Feed>> listFeeds() {
		
		List<Feed> feeds = feedService.listFeeds();
		
		if(!feeds.isEmpty()) {
			return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Feed>>(HttpStatus.NO_CONTENT);
		}
		
		
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
}
