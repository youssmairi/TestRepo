package com.demo.api.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.api.entity.Product;

@Repository
public interface RepositoryProduct extends JpaRepository<Product, Long>{
	@Query("select p from Product p where p.category.id = :codeCat and p.stock > :nbre")
	public List<Product> findByCategoryAndByStockGreaterThan(@Param("codeCat") Long codeCat,@Param("nbre") int nbre);
}
