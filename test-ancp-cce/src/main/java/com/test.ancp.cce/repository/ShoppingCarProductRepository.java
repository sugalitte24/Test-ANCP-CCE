package com.test.ancp.cce.repository;

import com.test.ancp.cce.model.ShoppingCar;
import com.test.ancp.cce.model.ShoppingCarProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShoppingCarProductRepository extends JpaRepository<ShoppingCarProduct, UUID> {
    List<ShoppingCarProduct> findShoppingCarProductByShoppingCar(ShoppingCar shoppingCar);
    void deleteByProductUuid(UUID productUuid);
}
