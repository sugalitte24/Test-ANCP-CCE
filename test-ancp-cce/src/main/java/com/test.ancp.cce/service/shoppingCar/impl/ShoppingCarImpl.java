package com.test.ancp.cce.service.shoppingCar.impl;

import com.test.ancp.cce.dto.products.ProductsDto;
import com.test.ancp.cce.dto.shoppingCar.ShoppingCarDto;
import com.test.ancp.cce.dto.shoppingCar.ShoppingCarMapper;
import com.test.ancp.cce.dto.shoppingCar.ShoppingCarRequest;
import com.test.ancp.cce.dto.shoppingCarProducts.ShoppingCarProductMapper;
import com.test.ancp.cce.dto.shoppingCarProducts.ShoppingCarProductRequest;
import com.test.ancp.cce.model.Products;
import com.test.ancp.cce.model.ShoppingCar;
import com.test.ancp.cce.model.ShoppingCarProduct;
import com.test.ancp.cce.repository.ProductsRepository;
import com.test.ancp.cce.repository.ShoppingCarProductRepository;
import com.test.ancp.cce.repository.ShoppingCarRepository;
import com.test.ancp.cce.service.product.ProductService;
import com.test.ancp.cce.service.shoppingCar.ShoppingCarService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCarImpl implements ShoppingCarService {

    private final ShoppingCarRepository shoppingCarRepository;
    private final ShoppingCarMapper shoppingCarMapper;
    private final ProductsRepository productsRepository;
    private final ProductService productService;
    private final ShoppingCarProductRepository shoppingCarProductRepository;

    public ShoppingCarImpl(ShoppingCarRepository shoppingCarRepository, ShoppingCarMapper shoppingCarMapper, ProductsRepository productsRepository,
                           ProductService productService, ShoppingCarProductRepository shoppingCarProductRepository) {
        this.shoppingCarRepository = shoppingCarRepository;
        this.shoppingCarMapper = shoppingCarMapper;
        this.productsRepository = productsRepository;
        this.productService = productService;
        this.shoppingCarProductRepository = shoppingCarProductRepository;
    }

    @Transactional(readOnly = true)
    public List<ShoppingCarDto> findAll() {
        List<ShoppingCar> shopping  = shoppingCarRepository.findAll();
        List<ShoppingCarDto> response =shoppingCarMapper.toDtoList(shopping);

        for(ShoppingCarDto shoppingCarDto: response){
            Optional<ShoppingCar> model = shoppingCarRepository.findById(shoppingCarDto.getUuid());
            List<ShoppingCarProduct> details = shoppingCarProductRepository.findShoppingCarProductByShoppingCar(model.get());
            List<ProductsDto> products = new ArrayList<>();

            for(ShoppingCarProduct detail: details){
                Optional<Products> productModel = productsRepository.findById(detail.getProductUuid());
                ProductsDto product = new ProductsDto();
                product.setUuid(detail.getProductUuid());
                product.setName(productModel.get().name);
                product.setAmount(detail.getQuantity());
                product.setPrice(detail.getPrice());
                product.setCreatedAt(productModel.get().getCreatedAt());
                product.setLastModifiedAt(productModel.get().getLastModifiedAt());
                products.add(product);
            }
            shoppingCarDto.setProducts(products);
        }
        return response;
    }

    @Transactional(readOnly = true)
    public ShoppingCarDto findByUuid(UUID productUuid) {
        Optional<ShoppingCar> response = Optional.ofNullable(shoppingCarRepository.findById(productUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro de compras no Encontrado")));

        ShoppingCarDto shoppingCarDto = new ShoppingCarDto();
        if(response.isPresent()){
            shoppingCarDto = shoppingCarMapper.toDtoFromModel(response.get());
        }
        List<ShoppingCarProduct> details = shoppingCarProductRepository.findShoppingCarProductByShoppingCar(response.get());
        List<ProductsDto> products = new ArrayList<>();
        for(ShoppingCarProduct detail: details){
            Optional<Products> productModel = productsRepository.findById(detail.getProductUuid());
            ProductsDto product = new ProductsDto();
            product.setUuid(detail.getProductUuid());
            product.setName(productModel.get().name);
            product.setAmount(detail.getQuantity());
            product.setPrice(detail.getPrice());
            product.setCreatedAt(productModel.get().getCreatedAt());
            product.setLastModifiedAt(productModel.get().getLastModifiedAt());
            products.add(product);
        }
        shoppingCarDto.setProducts(products);

        return shoppingCarDto;
    }

    @Transactional
    public String save(ShoppingCarRequest request) {
        ShoppingCar model = new ShoppingCar();
        model.setDateOrder(LocalDateTime.now());
        ShoppingCar save = shoppingCarRepository.save(model);
        saveDetailShoppingCar(request,save);
        return "Carro de compras creado.";

    }

    @Transactional
    public void saveDetailShoppingCar(ShoppingCarRequest request, ShoppingCar model ){
        Double total = 0.0;
        for (ShoppingCarProductRequest products: request.getProducts()) {
            Optional<Products> product = Optional.ofNullable(productsRepository.findById(products.getProductUuid())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no Encontrado")));
            if(product.isPresent()){
                if(product.get().getAmount() != 0){
                    ShoppingCarProduct detail = new ShoppingCarProduct();
                    detail.setProductUuid(products.getProductUuid());
                    detail.setShoppingCar(model);
                    detail.setQuantity(products.getQuantity());
                    detail.setPrice(product.get().getPrice());
                    Double subTotal = product.get().getPrice() * products.getQuantity();
                    total = total+subTotal;
                    shoppingCarProductRepository.save(detail);

                    productService.updateAmount(product.get().uuid,detail.quantity);
                    model.setTotal(total);
                }
            }
            else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El producto " + product.get().name+" no tiene stock");
            }
        }
    }

    @Transactional
    public ShoppingCarDto update(UUID shoppingUuid, ShoppingCarRequest request) {
        Optional<ShoppingCar> model = shoppingCarRepository.findById(shoppingUuid);
        if(model.isPresent()){
            List<ShoppingCarProduct> details = shoppingCarProductRepository.findShoppingCarProductByShoppingCar(model.get());
            for(ShoppingCarProductRequest shoppingCarRequest: request.getProducts()){
                for(ShoppingCarProduct detail: details){

                    Optional<Products> product = productsRepository.findById(shoppingCarRequest.getProductUuid());
                    detail.setProductUuid(shoppingCarRequest.getProductUuid());
                    detail.setQuantity(shoppingCarRequest.getQuantity());
                    detail.setPrice(product.get().getPrice());
                    shoppingCarProductRepository.save(detail);
                }
            }
            return shoppingCarMapper.toDtoFromModel(shoppingCarRepository.save(model.get()));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro de compras no Encontrado");
        }
    }

    @Transactional
    public void delete(UUID productUuid) {
        Optional<ShoppingCar> model = shoppingCarRepository.findById(productUuid);
        if(model.isPresent()){
            shoppingCarRepository.deleteById(productUuid);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro de compras no Encontrado");
        }
    }

    @Transactional
    public void deleteProductShoppingCar(UUID shoppingUuid, UUID productUuid) {
        Optional<ShoppingCar> model = shoppingCarRepository.findById(shoppingUuid);

        if(model.isPresent()){
            List<ShoppingCarProduct> details = shoppingCarProductRepository.findShoppingCarProductByShoppingCar(model.get());
            for(ShoppingCarProduct shopping: details){
                if(shopping.getProductUuid().equals(productUuid)){
                    shoppingCarProductRepository.deleteByProductUuid(productUuid);
                }
            }
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro de compras no Encontrado");
        }
    }

}
