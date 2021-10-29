package com.flashk.apis.rsstracker.controllers.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.flashk.apis.rsstracker.controllers.validations.Create;
import com.flashk.apis.rsstracker.controllers.validations.Update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Feed {
	
	private String id;
	private String title;
	private String description;
	private String link;
	
	@NotNull(message = "Source link is mandatory", groups = { Create.class, Update.class})
	private Link sourceLink;
	private Boolean isEnabled;
	
	// Auditing fields
	
	private Date createdDate;
	private Date lastModifiedDate;
	
}
