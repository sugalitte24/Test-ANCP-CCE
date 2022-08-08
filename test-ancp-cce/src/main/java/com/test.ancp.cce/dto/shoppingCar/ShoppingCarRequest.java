package com.test.ancp.cce.dto.shoppingCar;

import com.test.ancp.cce.dto.products.ProductsDto;
import com.test.ancp.cce.dto.shoppingCarProducts.ShoppingCarProductRequest;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCarRequest {
    public List<ShoppingCarProductRequest> products;
}
