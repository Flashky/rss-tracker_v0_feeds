package com.flashk.apis.rsstracker.controllers.model;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PagedResponse<T> {

	private List<T> data;
	private Pagination page;
	
	@JsonIgnore
	public boolean isEmpty() {
		return CollectionUtils.isEmpty(data);
	}
}
