package com.test.ancp.cce.dto.shoppingCarProducts;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCarProductDto {
    public UUID uuid;
    public LocalDateTime createdAt;
    public LocalDateTime lastModifiedAt;
    public UUID productUuid;
    public UUID shoppingCarUuid;
    public Long quantity;
    public UUID shoppingCar;
    public Double price;

}
