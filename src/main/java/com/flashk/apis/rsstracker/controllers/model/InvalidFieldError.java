package com.flashk.apis.rsstracker.controllers.model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidFieldError extends ApiError {

	private String object;
	private String field;
}