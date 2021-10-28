package com.flashk.apis.rsstracker.services;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.flashk.apis.rsstracker.controllers.exceptions.InvalidRssException;
import com.flashk.apis.rsstracker.controllers.exceptions.RssNotFoundException;
import com.flashk.apis.rsstracker.controllers.exceptions.TechnicalException;
import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.controllers.model.PagedResponse;
import com.flashk.apis.rsstracker.controllers.model.Pagination;
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
	public PagedResponse<Feed> listFeeds(Pageable pageable) {

		Page<FeedEntity> feedEntitiesPage = feedRepository.findAll(pageable);
		
		// Map data and pagination objects
		List<Feed> feeds = feedMapper.map(feedEntitiesPage.getContent());
		Pagination pagination = new Pagination(feeds.size(), feedEntitiesPage.getNumber(), feedEntitiesPage.getTotalElements());

		return PagedResponse.<Feed>builder().data(feeds).page(pagination).build();
		
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
	public Feed createFeed(Feed feed) {
		
		// Validate and obtain RSS data
		SyndFeed syndFeed = readRss(feed);
		
		// Complete data
		// By default, service will insert input data. 
		// If no input data is set, it will attempt to retrieve from the RSS feed.

		if(!StringUtils.hasText(feed.getTitle())) {
			feed.setTitle(syndFeed.getTitle());
		}
		
		if(!StringUtils.hasText(feed.getDescription())) {
			feed.setDescription(syndFeed.getDescription());
		}
		
		if(!StringUtils.hasText(feed.getLink())) {
			feed.setLink(syndFeed.getLink());
		}
		
		// Prepare entity to save
		FeedEntity feedEntity = feedMapper.map(feed);
		feedEntity.setId(null);
		
		FeedEntity feedEntityResult = feedRepository.save(feedEntity);
		
		return feedMapper.map(feedEntityResult);
	}

	@Override
	public void deleteFeed(String feedId) {
		
		if(!feedRepository.existsById(feedId)) {
			throw new RssNotFoundException();
		}
		
		feedRepository.deleteById(feedId);
	}
	
	protected SyndFeed readRss(Feed feed) {
		
		try {
			
			URL feedUri = new URL(feed.getSourceLink().getHref());
			SyndFeedInput input = new SyndFeedInput();	
			return input.build(new XmlReader(feedUri));
		
		} catch (IllegalArgumentException | IOException | FeedException e) {
			throw new InvalidRssException(e);
		} catch (Exception e) {
			throw new TechnicalException(e);
		}
		
	}



}
