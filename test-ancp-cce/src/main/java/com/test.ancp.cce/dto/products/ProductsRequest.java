package com.test.ancp.cce.dto.products;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsRequest {
    public String name;
    public Long amount;
    public Double price;
}
