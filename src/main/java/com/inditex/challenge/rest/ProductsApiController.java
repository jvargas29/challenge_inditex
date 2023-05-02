package com.inditex.challenge.rest;

import com.inditex.challenge.api.ProductsApi;
import com.inditex.challenge.model.ProductResponse;
import com.inditex.challenge.service.business.ProductVisibilityService;
import com.inditex.challenge.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsApiController implements ProductsApi {


    private final ProductVisibilityService productsService;

    @Autowired
    public ProductsApiController(ProductVisibilityService productsService) {
        this.productsService = productsService;
    }

    @Override
    public ResponseEntity<ProductResponse> getAvailableProducts() {
        ProductResponse productResponse = ProductMapper.INSTANCE.productToProductResponse(productsService.getProductsVisibles());
        return ResponseEntity.ok().body(productResponse);
    }
}
