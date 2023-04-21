package com.demo.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.dto.ProductDto;
import com.demo.api.service.ServiceProduct;

@RestController
@RequestMapping("/apiProduct")
public class ProductController {

	@Autowired
	private ServiceProduct serviceProduct;
	
	@GetMapping("/products/{codeCat}")
	private ResponseEntity<List<ProductDto>> findProductByCategory(@PathVariable ("codeCat") Long codeCat) {
		return Optional
			        .ofNullable( serviceProduct.findByCategoryAndByStockGreaterThan(codeCat, 0) )
			        .map( user -> ResponseEntity.ok().body(user) )          //200 OK
		            .orElseGet( () -> ResponseEntity.notFound().build() );  //404 Not found
	}
	

	
    //Creation des Produits pour enrichir la base et pouvoir mieux tester 
    @PostMapping("/products")
    public List <ProductDto> createProducts(@RequestBody List <ProductDto> productsDto) {
        return serviceProduct.saveAll(productsDto);
    }
	
	
}
