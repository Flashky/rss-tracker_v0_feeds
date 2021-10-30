package com.flashk.apis.rsstracker.controllers.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.flashk.apis.rsstracker.controllers.validations.Create;
import com.flashk.apis.rsstracker.controllers.validations.Update;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a link anchor.
 * @author Flashk
 *
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Link {

	@NotBlank(message = "Mandatory missing field", groups = { Create.class, Update.class})
	private String href;
	private String rel;
	private String type;
	
}
