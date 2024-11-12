package com.carlos.bcnctest.service;


import org.openapitools.model.PriceResponseDto;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public interface PricesService {

    PriceResponseDto getPrice(Integer productId, OffsetDateTime applicationDate, Integer brandId);

}
