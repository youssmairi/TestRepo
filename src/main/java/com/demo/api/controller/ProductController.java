package com.demo.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.api.dto.ProductDto;
import com.demo.api.service.ServiceProduct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/apiProduct")
public class ProductController {

	@Autowired
	private ServiceProduct serviceProduct;

	@GetMapping("/products/{codeCat}")
	private List<ProductDto> findProductByCategory(
			@PathVariable("codeCat") @Positive Long codeCat) {
		return serviceProduct.findByCategoryAndByStockGreaterThan(codeCat, 0);
	}

	// Creation des Produits pour enrichir la base et pouvoir mieux tester
	@PostMapping("/products")
	public List<ProductDto> createProducts(@RequestBody @Valid  List<ProductDto> productsDto) {
		return serviceProduct.saveAll(productsDto);
	}

}
