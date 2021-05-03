package com.flashk.apis.rsstracker.services;

import java.util.List;
import java.util.Optional;

import com.flashk.apis.rsstracker.controllers.model.Feed;

public interface FeedService {

	List<Feed> listFeeds();
	Optional<Feed> getFeed(String feedId);
	
	/** 
	 * Creates a feed.
	 * @param feed The feed to be created.
	 * @return The id of the created feed.
	 */
	String createFeed(Feed feed);
	
}
