package com.test.ancp.cce.dto.shoppingCar;

import com.test.ancp.cce.dto.products.ProductsDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCarDto {
    public UUID uuid;
    public LocalDateTime createdAt;
    public LocalDateTime lastModifiedAt;
    public LocalDateTime dateOrder;
    public List<ProductsDto> products;
    public Double total;

}
