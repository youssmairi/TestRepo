package com.demo.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	private Long id;
	
	@NotBlank
	@Size(max = 30)
	private String name ;
	
	@NotBlank
	@Size(max = 30)
	private String marque;
	
	@Min(value = 0L, message = "The value must be positive")
	private Integer stock;
	
	
	@ManyToOne
	private Category category;
}
