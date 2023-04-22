package com.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductDto {

	
	private @Valid Long id;
	
	@NotBlank
	@Size(max = 30)
	
	private String name ;
	
	@NotBlank
	@Size(max = 30)
	
	private String marque;
	
	@Min(value = 0L, message = "The value must be positive")
	
	private @Valid Integer stock; 
	
	@JsonProperty("category")
	private @Valid CategoryDto categoryDto;
}
