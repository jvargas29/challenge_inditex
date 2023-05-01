package com.inditex.challenge.service.business;

import com.inditex.challenge.model.business.Product;
import com.inditex.challenge.service.ProductService;
import com.inditex.challenge.service.business.stategy.VisibilityContext;
import com.inditex.challenge.service.business.stategy.VisibilityStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductVisibilityService {

    ProductService productService;
    @Autowired
    public ProductVisibilityService(ProductService productService) {
        this.productService = productService;
    }

    public String getProductsVisibles() {
        List<Product> productList = productService.getAllProducts();

        productList = productList.stream()
                .filter(this::isVisibleProduct)
                .toList();

        productList = sortBySequenceAsc(productList);
        return convertProductsListToString(productList);
    }

    private boolean isVisibleProduct(Product product) {
        VisibilityStrategy commissionStrategy = VisibilityContext.getStrategy(product);
        return commissionStrategy.isVisible(product.getSizes());
    }

    private String convertProductsListToString(List<Product> productDtos) {
        return productDtos.stream()
                .map(Product::getId)
                .map(Long::toUnsignedString)
                .collect(Collectors.joining(","));
    }

    private List<Product> sortBySequenceAsc(List<Product> productDtos) {
        return productDtos.stream()
                .sorted(Comparator.comparing(Product::getSequence))
                .toList();
    }

}
