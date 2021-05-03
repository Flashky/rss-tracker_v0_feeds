package com.flashk.apis.rsstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
@EnableMongoAuditing // Support for @CreatedDate on Documents
public class RssTrackerV0FeedsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RssTrackerV0FeedsApplication.class, args);
	}

}
