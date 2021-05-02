package com.flashk.apis.rsstracker.controllers.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Feed {
	
	private String id;
	private Date createdDate;
	private Date lastModifiedDate;
	
	private String url;
	private String description;
	private Boolean isEnabled;
	
	
}
