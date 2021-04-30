package com.flashk.apis.rsstracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Feed> listFeeds() {
		
		List<Feed> results = feedService.listFeeds();
		return results;
	}
	
}
