package com.flashk.apis.rsstracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.repositories.FeedRepository;
import com.flashk.apis.rsstracker.repositories.entities.FeedEntity;
import com.flashk.apis.rsstracker.services.mappers.FeedMapper;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired 
	private FeedRepository feedRepository;
	
	@Autowired
	private FeedMapper feedMapper;
	
	@Override
	public List<Feed> listFeeds() {

		List<FeedEntity> feedEntities = feedRepository.findAll();
		return feedMapper.map(feedEntities);
		
	}

	@Override
	public Optional<Feed> getFeed(String feedId) {
		
		Optional<FeedEntity> feedEntity = feedRepository.findById(feedId);
		
		Feed result = null;
		if(feedEntity.isPresent()) {
			result = feedMapper.map(feedEntity.get());
		} 
		
		return Optional.ofNullable(result);
	}

	@Override
	public String createFeed(Feed feed) {
		
		// TODO mandatory field validation
		// - url
		// - description
		// TODO validate RSS url
	
		FeedEntity feedEntity = feedMapper.map(feed);
		
		// Input id must be ignored
		feedEntity.setId(null);
		
		// All feeds are enabled by default
		if(feedEntity.getIsEnabled() == null) {
			feedEntity.setIsEnabled(true);
		}
		
		FeedEntity feedEntityResult = feedRepository.save(feedEntity);
		
		return feedEntityResult.getId();
	}

}
