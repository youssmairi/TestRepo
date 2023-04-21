package com.demo.api.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.api.dto.ProductDto;
import com.demo.api.entity.Category;
import com.demo.api.entity.Product;
import com.demo.api.persistence.RepositoryCategory;
import com.demo.api.persistence.RepositoryProduct;
import com.demo.api.utils.MapperUtil;

@Service
public class ServiceProductImpl implements ServiceProduct {
	
	@Autowired
	private RepositoryProduct repositoryProduct ;
	
	@Autowired
	private RepositoryCategory repositoryCategory;


	@Override
	public ProductDto save(ProductDto productDto) {
		Category category = null;
		if (productDto.getCategoryDto()!=null) {
			
			category = repositoryCategory.findByNameIgnoreCase(productDto.getCategoryDto().getName());
			
			category= category == null ?
					  repositoryCategory.save(MapperUtil.mapToCategoryEntity(productDto.getCategoryDto())):
					  category;
			
			productDto.getCategoryDto().setCode(category.getCode());
		}
		Product product = repositoryProduct.save(MapperUtil.mapToEntity(productDto));
		productDto.setId(product.getId());
		return productDto;
	}


	@Override
	public List<ProductDto> findByCategoryAndByStockGreaterThan(Long codeCat, int nbre) {
		List<Product>  products =  repositoryProduct.findByCategoryAndByStockGreaterThan(codeCat, nbre);
		return products.stream().map(MapperUtil :: mapToDto)
									.collect(Collectors.toList());
	}


	@Override
	public List<ProductDto> saveAll(List<ProductDto> productsDto) {
		return productsDto.stream().map(this :: save).collect(Collectors.toList());
		
	}


	
	

}
