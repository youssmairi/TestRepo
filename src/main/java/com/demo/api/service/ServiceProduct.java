package com.demo.api.service;

import java.util.List;

import com.demo.api.dto.ProductDto;

public interface ServiceProduct {

	public List<ProductDto> findByCategoryAndByStockGreaterThan(Long codeCat, int nbre);

	public ProductDto save(ProductDto productDto);

	public List<ProductDto> saveAll(List<ProductDto> productsDto);
}
