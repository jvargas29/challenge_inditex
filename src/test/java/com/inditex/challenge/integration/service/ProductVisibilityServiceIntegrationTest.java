package com.inditex.challenge.integration.service;

import com.inditex.challenge.model.repository.ProductRepository;
import com.inditex.challenge.service.ProductService;
import com.inditex.challenge.service.business.ProductVisibilityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductVisibilityServiceIntegrationTest {

	ProductRepository productRepository;
	ProductService productService;
	ProductVisibilityService productVisibilityService;

	@Autowired
	public ProductVisibilityServiceIntegrationTest(ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.productService = new ProductService(productRepository);
		this.productVisibilityService = new ProductVisibilityService(productService);
	}

	@Test
	void algorithmVisibleStrategyTest() {
		String products = productVisibilityService.getProductsVisibles();
		assertEquals("5,1,3",products);
	}


}
