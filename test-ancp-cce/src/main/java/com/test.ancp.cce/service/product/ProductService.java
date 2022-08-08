package com.test.ancp.cce.service.product;

import com.test.ancp.cce.dto.products.ProductsDto;
import com.test.ancp.cce.dto.products.ProductsRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductsDto> findAll();
    ProductsDto findByUuid(UUID productUuid);
    ProductsDto save(ProductsRequest request);
    ProductsDto update(UUID productUuid, ProductsRequest request);
    void delete( UUID productUuid);
    String updateAmount(UUID productUuid, Long quantity);
}
