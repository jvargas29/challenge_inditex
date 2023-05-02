package com.inditex.challenge.unit.service;

import com.inditex.challenge.model.business.Product;
import com.inditex.challenge.model.business.Size;
import com.inditex.challenge.model.entity.ProductEntity;
import com.inditex.challenge.model.entity.SizeEntity;
import com.inditex.challenge.model.entity.StockEntity;
import com.inditex.challenge.model.repository.ProductRepository;
import com.inditex.challenge.service.ProductService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    ProductService productService = new ProductService(productRepository);

    Product product;
    ProductEntity productEntity;

    @BeforeEach
    public void setup(){

        product = Product.builder()
                .id(1L)
                .sequence(10L)
                .sizes(setupSizesCommonValid())
                .build();

        productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setSequence(10L);
        productEntity.setSize(setupSizesEntitiesCommonValid());
    }
    @Test
    void whenGetProduct_thenReturnProduct_Ok(){
        when(productRepository.findAll()).thenReturn(List.of(productEntity));
        List<Product> productList = productService.getAllProducts();

        assertEquals(1, productList.size());
        assertEquals(3, productList.get(0).getSizes().size());
        assertEquals(product, productList.get(0));
        }

    @Test
    void whenGetProductEmpty_thenReturnEmpty(){
        when(productRepository.findAll()).thenReturn(Lists.newArrayList());
        List<Product> productList = productService.getAllProducts();

        assertTrue(productList.isEmpty());
    }

    private Set<Size> setupSizesCommonValid(){
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

    private Set<SizeEntity> setupSizesEntitiesCommonValid() {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSizeId(1L);
        stockEntity.setQuantity(10);

        StockEntity stockEntity2 = new StockEntity();
        stockEntity2.setSizeId(2L);
        stockEntity2.setQuantity(10);

        StockEntity stockEntity3 = new StockEntity();
        stockEntity3.setSizeId(3L);
        stockEntity3.setQuantity(10);

        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setSizeId(1L);
        sizeEntity.setProductId(1L);
        sizeEntity.setSpecial(false);
        sizeEntity.setBackSoon(false);
        sizeEntity.setStock(stockEntity);

        SizeEntity sizeEntity2 = new SizeEntity();
        sizeEntity2.setSizeId(2L);
        sizeEntity2.setProductId(1L);
        sizeEntity2.setSpecial(false);
        sizeEntity2.setBackSoon(false);
        sizeEntity2.setStock(stockEntity2);

        SizeEntity sizeEntity3 = new SizeEntity();
        sizeEntity3.setSizeId(3L);
        sizeEntity3.setProductId(1L);
        sizeEntity3.setSpecial(false);
        sizeEntity3.setBackSoon(false);
        sizeEntity3.setStock(stockEntity3);

        return Sets.newSet(sizeEntity,sizeEntity2,sizeEntity3);
    }

}
