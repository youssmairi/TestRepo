package com.demo.api.service;

import com.demo.api.entity.Category;
import com.demo.api.entity.Product;

public interface ServiceProduct {

	public Product findProduct(Category cat);
}
