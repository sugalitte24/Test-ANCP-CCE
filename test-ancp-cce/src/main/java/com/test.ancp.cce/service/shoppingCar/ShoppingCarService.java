package com.test.ancp.cce.service.shoppingCar;

import com.test.ancp.cce.dto.shoppingCar.ShoppingCarDto;
import com.test.ancp.cce.dto.shoppingCar.ShoppingCarRequest;

import java.util.List;
import java.util.UUID;

public interface  ShoppingCarService {
    List<ShoppingCarDto> findAll();
    ShoppingCarDto findByUuid(UUID shoppingUuid);
    String save(ShoppingCarRequest request);
    ShoppingCarDto update(UUID shoppingUuid, ShoppingCarRequest request);
    void delete( UUID shoppingUuid);
    void deleteProductShoppingCar( UUID shoppingUuid, UUID productUuid);
}
