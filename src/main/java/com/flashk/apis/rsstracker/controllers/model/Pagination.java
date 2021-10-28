package com.flashk.apis.rsstracker.controllers.model;

import org.springframework.util.Assert;

import lombok.Getter;

@Getter
public class Pagination {
	
	private long size;
	private long totalElements;
	private long totalPages;
	private long number;
	
	/**
	 * Creates a new {@link PageMetadata} from the given size, number, total elements and total pages.
	 *
	 * @param size
	 * @param number zero-indexed, must be less than totalPages
	 * @param totalElements
	 * @param totalPages
	 */
	public Pagination(long size, long number, long totalElements, long totalPages) {

		Assert.isTrue(size > -1, "Size must not be negative!");
		Assert.isTrue(number > -1, "Number must not be negative!");
		Assert.isTrue(totalElements > -1, "Total elements must not be negative!");
		Assert.isTrue(totalPages > -1, "Total pages must not be negative!");

		this.size = size;
		this.number = number;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	/**
	 * Creates a new {@link PageMetadata} from the given size, number and total elements.
	 *
	 * @param size the size of the page
	 * @param number the number of the page
	 * @param totalElements the total number of elements available
	 */
	public Pagination(long size, long number, long totalElements) {
		this(size, number, totalElements, size == 0 ? 0 : (long) Math.ceil((double) totalElements / (double) size));
	}
	
}
