package com.test.ancp.cce.service.product.impl;

import com.test.ancp.cce.dto.products.ProductMapper;
import com.test.ancp.cce.dto.products.ProductsDto;
import com.test.ancp.cce.dto.products.ProductsRequest;
import com.test.ancp.cce.model.Products;
import com.test.ancp.cce.repository.ProductsRepository;
import com.test.ancp.cce.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductsRepository productsRepository, ProductMapper productMapper) {
        this.productsRepository = productsRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public List<ProductsDto> findAll() {
        List<Products> response  = productsRepository.findAll();
        return productMapper.toDtoList(response);
    }

    @Transactional(readOnly = true)
    public ProductsDto findByUuid(UUID productUuid) {
        Optional<Products> response = Optional.ofNullable(productsRepository.findById(productUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no Encontrado")));
        ProductsDto productsDto = new ProductsDto();
        if(response.isPresent()){
            productsDto = productMapper.toDtoFromModel(response.get());
        }
        return productsDto;
    }

    @Transactional
    public ProductsDto save(ProductsRequest request) {
        Products model = productMapper.toModel(request);
        Products save = productsRepository.save(model);
        return productMapper.toDtoFromModel(save);
    }

    @Transactional
    public ProductsDto update(UUID productUuid, ProductsRequest request) {
        Optional<Products> model = productsRepository.findById(productUuid);
        if(model.isPresent()){
            productMapper.update(request,model.get());
            return productMapper.toDtoFromModel(productsRepository.save(model.get()));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no Encontrado");
        }
    }

    @Transactional
    public void delete(UUID productUuid) {
        Optional<Products> model = productsRepository.findById(productUuid);
        if(model.isPresent()){
            productsRepository.deleteById(productUuid);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no Encontrado");
        }
    }

    @Transactional
    public String updateAmount(UUID productUuid, Long quantity){
        Optional<Products> model = productsRepository.findById(productUuid);
        if(model.isPresent()){
            long newAmount = model.get().getAmount() - quantity;
            model.get().setAmount(newAmount);
            if(newAmount == 0){
                return "El producto"+ model.get().name+" ya no tiene stock";
            }
            productsRepository.save(model.get());
            return "Cantidad actualizada";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no Encontrado");
        }
    }
}
