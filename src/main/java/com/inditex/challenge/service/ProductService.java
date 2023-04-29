package com.inditex.challenge.service;

import com.inditex.challenge.model.business.Product;
import com.inditex.challenge.model.entity.ProductEntity;
import com.inditex.challenge.model.repository.ProductRepository;
import com.inditex.challenge.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        List<ProductEntity> products = productRepository.findAll();
       return ProductMapper.INSTANCE.getListEntityToProductList(products);
    }

}
