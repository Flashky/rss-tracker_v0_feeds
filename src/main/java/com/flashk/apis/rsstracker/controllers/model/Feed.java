package com.flashk.apis.rsstracker.controllers.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.flashk.apis.rsstracker.controllers.model.ValidationGroups.Create;
import com.flashk.apis.rsstracker.controllers.model.ValidationGroups.Update;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Feed {
	
	private String id;
	private Date createdDate;
	private Date lastModifiedDate;
	
	@NotBlank(message = "Feed url is mandatory", groups = { Create.class, Update.class})
	private String url;
	
	
	@NotBlank(message = "Description is mandatory" , groups = { Create.class, Update.class})
	private String description;
	
	private Boolean isEnabled;
	
	
}
