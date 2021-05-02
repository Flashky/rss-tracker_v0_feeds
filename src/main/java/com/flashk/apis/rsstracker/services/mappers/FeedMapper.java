package com.flashk.apis.rsstracker.services.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.flashk.apis.rsstracker.controllers.model.Feed;
import com.flashk.apis.rsstracker.repositories.entities.FeedEntity;

@Mapper(componentModel = "spring")
public interface FeedMapper {

	Feed map(FeedEntity feedEntity);
	List<Feed> map(List<FeedEntity> feedEntities);
	
}
