package com.flashk.apis.rsstracker.controllers.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * RFC 7807 compliant response class
 * @author bruizv
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc7807">RFC 7807</a>
 *
 */
@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class ErrorResponse {
	
	// RFC 7807 base fields
	private String type;
	private String title;
	private Integer status;
	private String detail;
	private String instance;
	
	// RFC 7807 extensions
	private List<InvalidFieldError> invalidParameters;
	
	
	
}
