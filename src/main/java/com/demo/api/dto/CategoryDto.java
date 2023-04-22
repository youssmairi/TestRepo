package com.demo.api.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CategoryDto {

	
	private @Valid Long code;
	
	@NotBlank
	@Size(max = 30)
	private String name ;

	
	private @Valid String description;
}
