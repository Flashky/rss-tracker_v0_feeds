package com.flashk.apis.rsstracker.repositories.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "rssFeeds", collation = "{ 'locale' :  'es' }")
public class FeedEntity {

	@Id
	private String id;
	
	@CreatedDate
	@JsonFormat(timezone = "GMT+02:00")
	private Date createdDate;
	
	@LastModifiedDate
	@JsonFormat(timezone = "GMT+02:00")
	private Date lastModifiedDate;
	
	private String url;
	private String description;
	private Boolean isEnabled;
	

	
}
