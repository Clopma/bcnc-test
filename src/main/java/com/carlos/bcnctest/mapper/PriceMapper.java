package com.carlos.bcnctest.mapper;

import com.carlos.bcnctest.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.PriceResponseDto;

@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface PriceMapper {

    @Mapping(source = "brand.brandId", target = "brandId")
    PriceResponseDto priceEntityToResponseDto(Price priceEntity);
}
