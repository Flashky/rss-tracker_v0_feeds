package com.flashk.apis.rsstracker.controllers.model;

import javax.validation.constraints.NotBlank;

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
public class Link {

	@NotBlank(message = "Link href is mandatory", groups = { Create.class, Update.class})
	private String href;
	private String rel;
	private String type;
	
}
