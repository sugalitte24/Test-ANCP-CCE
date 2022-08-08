package com.test.ancp.cce.dto.shoppingCar;

import com.test.ancp.cce.model.ShoppingCar;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ShoppingCarMapper {

    ShoppingCar toModel(ShoppingCarRequest request);

    ShoppingCarDto toDtoFromModel(ShoppingCar shoppingCar);

    List<ShoppingCarDto> toDtoList(List<ShoppingCar> shoppingCarList);

    ShoppingCar toModelFromDto(ShoppingCarDto shoppingCarDto);

    void update(ShoppingCarRequest request, @MappingTarget ShoppingCar shoppingCar);
}
