package com.test.ancp.cce.controller;

import com.test.ancp.cce.dto.shoppingCar.ShoppingCarDto;
import com.test.ancp.cce.dto.shoppingCar.ShoppingCarRequest;
import com.test.ancp.cce.service.shoppingCar.ShoppingCarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("shopping-car.v1.crud")
@RequestMapping("v1/shopping-car")
@Validated
public class ShoppingCarController {
    private final ShoppingCarService shoppingCarService;

    public ShoppingCarController(ShoppingCarService shoppingCarService) {
        this.shoppingCarService = shoppingCarService;
    }

    @GetMapping
    List<ShoppingCarDto> findAll() {
        return shoppingCarService.findAll();
    }

    @GetMapping("/{shoppingUuid}")
    ResponseEntity<ShoppingCarDto> show(@PathVariable UUID shoppingUuid) {
        return ResponseEntity.ok().body(shoppingCarService.findByUuid(shoppingUuid));
    }

    @PostMapping
    ResponseEntity<String> create(@RequestBody ShoppingCarRequest request){
        return new ResponseEntity<>(shoppingCarService.save(request), HttpStatus.CREATED);
    }

    @PatchMapping("/update-product/{shoppingUuid}")
    ShoppingCarDto updateProduct(@PathVariable  UUID shoppingUuid, @RequestBody ShoppingCarRequest request){
        return shoppingCarService.update(shoppingUuid, request);
    }

    @DeleteMapping("/{shoppingUuid}")
    void delete(@PathVariable UUID shoppingUuid){
        shoppingCarService.delete(shoppingUuid);
    }

    @DeleteMapping("/{shoppingUuid}/product/{productUuid}")
    void deleteProductShoppingCar(@PathVariable UUID shoppingUuid,
                                  @PathVariable UUID productUuid){
        shoppingCarService.deleteProductShoppingCar(shoppingUuid, productUuid);
    }
}
