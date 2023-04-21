package com.demo.api.utils;

import com.demo.api.dto.CategoryDto;
import com.demo.api.dto.ProductDto;
import com.demo.api.entity.Category;
import com.demo.api.entity.Product;

public class MapperUtil {
	
	public static ProductDto mapToDto(Product product) {
		ProductDto productDto= ProductDto.builder().id(product.getId())
													.marque(product.getMarque())
													.name(product.getName())
													.stock(product.getStock())
													.build();
		if (product.getCategory()!=null) {
			CategoryDto categoryDto = mapToCategoryDto (product.getCategory());
			productDto.setCategoryDto(categoryDto);
		}
		return productDto;
		
	}
	
	public static Product mapToEntity(ProductDto productDto) {
		
		Product product = Product.builder().marque(productDto.getMarque())
				.name(productDto.getName())
				.stock(productDto.getStock())
				.build();
		
		if (productDto.getCategoryDto()!=null) {
			Category category= mapToCategoryEntity (productDto.getCategoryDto());
			product.setCategory(category);
		}
		
		return product;
															
	}
	
	private static CategoryDto mapToCategoryDto(Category category) {
		return CategoryDto.builder().code(category.getCode())
									.name(category.getName())
									.description(category.getDescription())
									.build();
		
	}
	
	public static Category mapToCategoryEntity(CategoryDto categoryDto) {
		return Category.builder().code(categoryDto.getCode())
								 .name(categoryDto.getName())
								 .description(categoryDto.getDescription())
								 .build();
		
	}
	
	

}
