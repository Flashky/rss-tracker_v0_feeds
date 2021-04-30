package com.flashk.apis.rsstracker.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flashk.apis.rsstracker.repositories.entities.FeedEntity;

public interface FeedRepository extends MongoRepository<FeedEntity, String> {

}