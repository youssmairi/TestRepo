package com.demo.api.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Category {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@NotBlank
	@Size(max = 30)
	private String name ;

	@Size(max = 30)
	private String description;
	
	@OneToMany 
	private List<Product> products;
	
	
	
}
