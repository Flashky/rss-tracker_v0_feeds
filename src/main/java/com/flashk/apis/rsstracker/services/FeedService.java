package com.flashk.apis.rsstracker.services;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.controllers.model.PagedResponse;

public interface FeedService {

	PagedResponse<Feed> listFeeds(Pageable pageable);
	Optional<Feed> getFeed(String feedId);
	
	/** 
	 * Creates a feed.
	 * @param feed The feed to be created.
	 * @return the created feed.
	 */
	Feed createFeed(Feed feed);
	
	void deleteFeed(String feedId);	
}
