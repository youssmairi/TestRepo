package com.demo.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.demo.api.dto.CategoryDto;
import com.demo.api.dto.ProductDto;
import com.demo.api.entity.Category;
import com.demo.api.entity.Product;
import com.demo.api.persistence.RepositoryCategory;
import com.demo.api.persistence.RepositoryProduct;


@ExtendWith(MockitoExtension.class)
public class ServiceproductTest {
	
	 @InjectMocks
	 private ServiceProductImpl personService;

	 @Mock 
	 private RepositoryProduct repositoryProduct;
	 
	 @Mock 
	 private RepositoryCategory repositoryCategory;

	 private List<Product> products;
	 private List<Product> productCat1;

	 @BeforeEach
	 void setUp(){
		 products = new ArrayList<>();
		 productCat1 = new ArrayList<>();
		 Category category1 = Category.builder().name("ELECTROMENAGER")
				 								.code(Long.valueOf(1))
				 								.build();
		 Category category2 = Category.builder().name("SPORT")
				                                .code(Long.valueOf(2))
					                            .build();
		 
		 Category category3 = Category.builder().name("BOISSON")
				 								.code(Long.valueOf(3))
					                            .build();
		 
		 Product product1 = Product.builder().name("machine a laver")
				 								.id(Long.valueOf(12))
				 								.stock(0)
				 								.category(category1)
				 								.build();
		 
		 Product product2 = Product.builder().name("aspirateur")
					.stock(10)
					.category(category1)
					.build();

		 Product product3 = Product.builder().name("televiseur")
					.stock(8)
					.category(category1)
					.build();
		 
		 Product product4 = Product.builder().name("chaussure")
				 							 .stock(10)
				 							.category(category2)
				 							.build();							
																		
		Product product5 = Product.builder().name("ballon")
											.stock(5)
											.category(category2)
											.build();
		
		Product product6 = Product.builder().name("racette tinis")
											.stock(0)
											.category(category2)
											.build();
		
		Product product7 = Product.builder().name("eau minirale")
											.stock(11)
											.category(category3)
											.build();

		Stream.of(product1,product2,product3,product4,product5,product6,product7).forEach(p -> products.add(p));
		Stream.of(product2,product3).forEach(p -> productCat1.add(p));
	 }
			

	 @Test 
	 void test_findByCategoryAndByStockGreaterThan(){
		 
		//GUIVEN
		when(repositoryProduct.findByCategoryAndByStockGreaterThan(anyLong(), anyInt())).thenReturn(productCat1);
		
		//WHEN
		List<ProductDto> productDtos = personService.findByCategoryAndByStockGreaterThan(Long.valueOf(1), 0);
		
		//THEN
		assertThat(productDtos.get(0).getStock()).isNotNull();
		assertTrue(productDtos.get(0).getStock()>0);
		assertThat(productCat1.size()).isEqualTo(productDtos.size());
		verify(repositoryProduct, times(1)).findByCategoryAndByStockGreaterThan(anyLong(), anyInt());
		   
	 }
	 
	 @Test 
	 void test_saveProduct(){
		//GUIVEN
		 CategoryDto categoryDto = CategoryDto.builder().name("test").build();
		 ProductDto productDtoTest = ProductDto.builder().name("test").categoryDto(categoryDto).build();
		 when(repositoryCategory.save(Mockito.any())).thenReturn(products.get(0).getCategory());
		 when(repositoryCategory.findByNameIgnoreCase("test")).thenReturn(null);
		 when(repositoryProduct.save(Mockito.any())).thenReturn(products.get(0));
		
		//WHEN
		ProductDto productDto = personService.save(productDtoTest);
		
		//THEN
		verify(repositoryProduct, times(1)).save(Mockito.any());
		verify(repositoryCategory, times(1)).findByNameIgnoreCase(Mockito.any());
		verify(repositoryCategory, times(1)).save(Mockito.any());
		assertThat(productDto.getId()).isNotNull();
		assertThat(productDto.getId()).isEqualTo(products.get(0).getId());
		assertThat(productDto.getCategoryDto().getCode()).isNotNull();
		assertThat(productDto.getCategoryDto().getCode()).isEqualTo(products.get(0).getCategory().getCode());
		
		
		   
	 }


}

