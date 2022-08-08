package com.test.ancp.cce.dto.products;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsDto {
    public UUID uuid;
    public LocalDateTime createdAt;
    public LocalDateTime lastModifiedAt;
    public String name;
    public Long amount;
    public Double price;
}
