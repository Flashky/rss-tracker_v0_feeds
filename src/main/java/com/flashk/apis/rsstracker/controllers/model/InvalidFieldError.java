package com.flashk.apis.rsstracker.controllers.model;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class InvalidFieldError extends ApiError {

	private String object;
	private String field;
}