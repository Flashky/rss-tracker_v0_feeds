package com.flashk.apis.rsstracker.controllers.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feed implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 416464946159171510L;
	
	private String id;
	private Date createdDate;
	private Date lastModifiedDate;
	
	private String url;
	private String description;
	private Boolean isEnabled;
	
	
}
