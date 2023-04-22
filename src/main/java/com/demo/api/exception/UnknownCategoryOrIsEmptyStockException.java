package com.demo.api.exception;

import lombok.Getter;

@Getter
public class UnknownCategoryOrIsEmptyStockException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long id;

	public UnknownCategoryOrIsEmptyStockException(Long id) {
		super(" La category avec id : " + id + " est inconnue ou le stock est vide  ");
		this.id = id;
	}

}