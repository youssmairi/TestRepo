package com.demo.api.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.api.entity.Category;

@Repository
public interface RepositoryCategory extends JpaRepository<Category, Long> {

	public Category findByNameIgnoreCase(String name);

}
