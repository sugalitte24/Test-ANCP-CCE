package com.test.ancp.cce.dto.shoppingCarProducts;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCarProductRequest {
    public UUID productUuid;
    public Long quantity;
}
