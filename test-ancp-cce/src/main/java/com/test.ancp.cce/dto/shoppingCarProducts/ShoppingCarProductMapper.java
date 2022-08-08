package com.test.ancp.cce.dto.shoppingCarProducts;

import com.test.ancp.cce.dto.shoppingCar.ShoppingCarDto;
import com.test.ancp.cce.dto.shoppingCar.ShoppingCarRequest;
import com.test.ancp.cce.model.ShoppingCarProduct;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ShoppingCarProductMapper {
    ShoppingCarProduct toModel(ShoppingCarRequest request);

    ShoppingCarDto toDtoFromModel(ShoppingCarProduct shoppingCarProduct);

    List<ShoppingCarDto> toDtoList(List<ShoppingCarProduct> shoppingCarProducts);

    void update(List<ShoppingCarProductRequest> request, @MappingTarget List<ShoppingCarProduct> shoppingCarProduct);
}
