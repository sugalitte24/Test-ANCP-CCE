package com.test.ancp.cce.model;

import com.test.ancp.cce.model.abstracts.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "shopping_car_products")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCarProduct extends BaseModel {
    public UUID productUuid;
    public Long quantity;
    public Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShoppingCar shoppingCar;
}
