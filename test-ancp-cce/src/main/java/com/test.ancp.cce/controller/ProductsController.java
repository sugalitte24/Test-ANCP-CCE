package com.test.ancp.cce.controller;

import com.test.ancp.cce.dto.products.ProductsDto;
import com.test.ancp.cce.dto.products.ProductsRequest;
import com.test.ancp.cce.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("products.v1.crud")
@RequestMapping("v1/products")
@Validated
public class ProductsController {
    private final ProductService productsService;

    public ProductsController(ProductService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
     List<ProductsDto> findAll() {
        return productsService.findAll();
    }

    @GetMapping("/{productUuid}")
    ResponseEntity<ProductsDto> show(@PathVariable  UUID productUuid) {
        return ResponseEntity.ok().body(productsService.findByUuid(productUuid));
    }

    @PostMapping
    ResponseEntity<ProductsDto> create(@RequestBody ProductsRequest request){
        return new ResponseEntity<>(productsService.save(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{productUuid}")
    ProductsDto update(@PathVariable  UUID productUuid, @RequestBody ProductsRequest request){
        return productsService.update(productUuid, request);
    }

    @DeleteMapping("/{productUuid}")
    void delete(@PathVariable UUID productUuid){
        productsService.delete(productUuid);
    }
}
