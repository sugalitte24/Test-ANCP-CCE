package com.test.ancp.cce.repository;

import com.test.ancp.cce.model.ShoppingCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingCarRepository extends JpaRepository<ShoppingCar, UUID> {
}
