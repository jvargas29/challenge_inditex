package com.inditex.challenge.unit.service;

import com.inditex.challenge.model.business.Product;
import com.inditex.challenge.model.business.Size;
import com.inditex.challenge.service.ProductService;
import com.inditex.challenge.service.business.ProductVisibilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductVisibilityServiceTest {

    ProductService productService = Mockito.mock(ProductService.class);
    ProductVisibilityService productVisibilityService = new ProductVisibilityService(productService);
    List<Product> productListWithCommonSize = new ArrayList<>();

    List<Product> productListWithSpecialSize = new ArrayList<>();

    @BeforeEach
    public void setup(){

        productListWithCommonSize.add(Product.builder()
                .id(1L)
                .sequence(5L)
                .sizes(sizesProduct1())
                .build());

        productListWithCommonSize.add(Product.builder()
                .id(2L)
                .sequence(10L)
                .sizes(sizesProduct2())
                .build());

        productListWithCommonSize.add(Product.builder()
                .id(3L)
                .sequence(4L)
                .sizes(sizesProduct3())
                .build());


        productListWithSpecialSize.add(Product.builder()
                .id(1L)
                .sequence(5L)
                .sizes(sizesNoValidProductWith())
                .build());

        productListWithSpecialSize.add(Product.builder()
                .id(2L)
                .sequence(1L)
                .sizes(sizesValidProductWith())
                .build());

    }

    private Set<Size> sizesProduct1(){
        Size size = Size.builder()
                .sizeId(1L)
                .productId(1L)
                .special(false)
                .backSoon(false)
                .quantity(10)
                .build();

        Size size2 = Size.builder()
                .sizeId(2L)
                .productId(1L)
                .special(false)
                .backSoon(false)
                .quantity(10)
                .build();

        Size size3 = Size.builder()
                .sizeId(3L)
                .productId(1L)
                .special(false)
                .backSoon(false)
                .quantity(10)
                .build();
        return Sets.newSet(size,size2,size3);
    }

    private Set<Size> sizesProduct2(){
        Size size = Size.builder()
                .sizeId(4L)
                .productId(2L)
                .special(false)
                .backSoon(false)
                .quantity(0)
                .build();

        Size size2 = Size.builder()
                .sizeId(5L)
                .productId(2L)
                .special(false)
                .backSoon(false)
                .quantity(15)
                .build();

        Size size3 = Size.builder()
                .sizeId(6L)
                .productId(2L)
                .special(false)
                .backSoon(true)
                .quantity(100)
                .build();
        return Sets.newSet(size,size2,size3);
    }

    private Set<Size> sizesProduct3(){
        Size size = Size.builder()
                .sizeId(7L)
                .productId(2L)
                .special(false)
                .backSoon(true)
                .quantity(0)
                .build();

        Size size2 = Size.builder()
                .sizeId(8L)
                .productId(2L)
                .special(false)
                .backSoon(true)
                .quantity(0)
                .build();

        return Sets.newSet(size,size2);
    }

    private Set<Size> sizesNoValidProductWith(){
        Size size = Size.builder()
                .sizeId(9L)
                .productId(4L)
                .special(true)
                .backSoon(false)
                .quantity(0)
                .build();

        Size size2 = Size.builder()
                .sizeId(10L)
                .productId(4L)
                .special(false)
                .backSoon(false)
                .quantity(0)
                .build();

        return Sets.newSet(size,size2);
    }

    private Set<Size> sizesValidProductWith(){
        Size size = Size.builder()
                .sizeId(7L)
                .productId(2L)
                .special(true)
                .backSoon(true)
                .quantity(0)
                .build();

        Size size2 = Size.builder()
                .sizeId(8L)
                .productId(2L)
                .special(false)
                .backSoon(false)
                .quantity(10)
                .build();

        return Sets.newSet(size,size2);
    }
    @Test
    void givenThreeProductsCommonsValid_whenGetProductsVisibles_thenReturnThreeVisibleProducts(){
        when(productService.getAllProducts()).thenReturn(productListWithCommonSize);
        String productsVisibles = productVisibilityService.getProductsVisibles();

        assertEquals(3,productsVisibles.split(",").length);
        assertEquals(productListWithCommonSize.get(2).getId().toString(), Arrays.stream(productsVisibles.split(",")).toList().get(0));
        assertEquals(productListWithCommonSize.get(0).getId().toString(), Arrays.stream(productsVisibles.split(",")).toList().get(1));
        assertEquals(productListWithCommonSize.get(1).getId().toString(), Arrays.stream(productsVisibles.split(",")).toList().get(2));
    }

    @Test
    void givenTwoProductsWithOneSpecialValid_whenGetProductsVisibles_thenReturnOneVisibleProduct(){
        when(productService.getAllProducts()).thenReturn(productListWithSpecialSize);
        String productsVisibles = productVisibilityService.getProductsVisibles();

        assertEquals(1,productsVisibles.split(",").length);
        assertEquals(productListWithSpecialSize.get(1).getId().toString(), productsVisibles);
    }
}
