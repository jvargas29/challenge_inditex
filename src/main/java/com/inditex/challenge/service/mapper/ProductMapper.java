package com.inditex.challenge.service.mapper;

import com.inditex.challenge.model.ProductResponse;
import com.inditex.challenge.model.business.Product;
import com.inditex.challenge.model.business.Size;
import com.inditex.challenge.model.entity.ProductEntity;
import com.inditex.challenge.model.entity.SizeEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "productsId", source = "productList")
    ProductResponse productToProductResponse(String productList);

    @IterableMapping(qualifiedByName = "entityToProduct")
    List<Product> getListEntityToProductList(List<ProductEntity> products);
    @Named("entityToProduct")
    @Mapping(target = "sizes", source = "size",qualifiedByName = "getListEntityToSizeList")
    Product entityToProduct(ProductEntity priceEntity);

    @IterableMapping(qualifiedByName = "entityToSize")
    @Named("getListEntityToSizeList")
    Set<Size> getListEntityToSizeList(Set<SizeEntity> sizeEntities);

    @Named("entityToSize")
    @Mapping(target = "quantity", source = "stock.quantity", defaultValue = "0")
    Size entityToSize(SizeEntity sizeEntity);

}
