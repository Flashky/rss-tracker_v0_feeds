package com.flashk.apis.rsstracker.services.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.repositories.entities.FeedEntity;

@Mapper(componentModel = "spring")
public interface FeedMapper {

	Feed map(FeedEntity feedEntity);
	List<Feed> map(List<FeedEntity> feedEntities);
	
	@Mapping(source = "isEnabled", target = "isEnabled", defaultValue = "true")
	FeedEntity map(Feed feed);
	
}
