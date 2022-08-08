package com.test.ancp.cce.dto.products;

import com.test.ancp.cce.model.Products;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {
    Products toModel(ProductsRequest request);

    ProductsDto toDtoFromModel(Products products);

    List<ProductsDto> toDtoList(List<Products> productsList);

    void update(ProductsRequest request, @MappingTarget Products products);

}
