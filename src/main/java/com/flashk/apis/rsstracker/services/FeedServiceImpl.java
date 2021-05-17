package com.flashk.apis.rsstracker.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.flashk.apis.rsstracker.controllers.exceptions.InvalidRssException;
import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.repositories.FeedRepository;
import com.flashk.apis.rsstracker.repositories.entities.FeedEntity;
import com.flashk.apis.rsstracker.services.mappers.FeedMapper;
import com.flashk.apis.rsstracker.ws.rssvalidator.model.Envelope;

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
		
		// Check valid RSS feed

		WebClient webClient = WebClient.builder().build();
		
		URI validationUri = UriComponentsBuilder.fromHttpUrl("http://validator.w3.org/feed/check.cgi")
																.queryParam("url", feed.getUrl())
																.queryParam("output", "soap12")
																.build()
																.toUri();
		
		
		Envelope result = webClient.get().uri(validationUri)
				.retrieve().bodyToMono(Envelope.class).block();
		
		if(!result.getBody().getFeedValidationResponse().getValidity()) {
			throw new InvalidRssException();
		}
		
		// Prepare entity to save
		FeedEntity feedEntity = feedMapper.map(feed);
		feedEntity.setId(null);
		
		// All feeds are enabled by default
		if(feedEntity.getIsEnabled() == null) {
			feedEntity.setIsEnabled(true);
		}
		
		FeedEntity feedEntityResult = feedRepository.save(feedEntity);
		
		return feedEntityResult.getId();
	}

}
