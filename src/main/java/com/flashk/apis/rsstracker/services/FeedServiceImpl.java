package com.flashk.apis.rsstracker.services;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.flashk.apis.rsstracker.controllers.exceptions.InvalidRssException;
import com.flashk.apis.rsstracker.controllers.exceptions.TechnicalException;
import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.repositories.FeedRepository;
import com.flashk.apis.rsstracker.repositories.entities.FeedEntity;
import com.flashk.apis.rsstracker.services.mappers.FeedMapper;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

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
		
		// Validate and obtain RSS data
		SyndFeed syndFeed = readRss(feed);
		
		// Complete data
		if(!StringUtils.hasText(feed.getDescription())) {
			feed.setDescription(syndFeed.getTitle());
		}
		
		// Prepare entity to save
		FeedEntity feedEntity = feedMapper.map(feed);
		feedEntity.setId(null);
		
		FeedEntity feedEntityResult = feedRepository.save(feedEntity);
		
		return feedEntityResult.getId();
	}

	private SyndFeed readRss(Feed feed) {
		
		try {
			
			URL feedUri = new URL(feed.getUrl());
			SyndFeedInput input = new SyndFeedInput();	
			return input.build(new XmlReader(feedUri));
		
		} catch (IllegalArgumentException | IOException | FeedException e) {
			throw new InvalidRssException(e);
		} catch (Exception e) {
			throw new TechnicalException(e);
		}
		
	}

}
