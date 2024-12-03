package com.carlos.bcnctest.service;


import com.carlos.bcnctest.entities.Brand;
import com.carlos.bcnctest.entities.Price;
import com.carlos.bcnctest.exception.BusinessException;
import com.carlos.bcnctest.mapper.DateMapper;
import com.carlos.bcnctest.mapper.PriceMapper;
import com.carlos.bcnctest.repositories.BrandRepository;
import com.carlos.bcnctest.repositories.PriceRepository;
import lombok.extern.log4j.Log4j2;
import org.openapitools.model.PriceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Log4j2
public class PricesServiceImpl implements PricesService {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    PriceMapper priceMapper;

    @Autowired
    DateMapper dateMapper;

    @Override
    public PriceResponseDto getPrice(Integer productId, OffsetDateTime applicationDate, Integer brandId) {

        Brand brand = brandRepository.findById(brandId.longValue()).orElseThrow(() -> new BusinessException("Brand not found for id " + brandId, HttpStatus.NOT_FOUND));

        Price price = priceRepository.findTopByBrandEqualsAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdEqualsOrderByPriorityDesc(
                brand, applicationDate, applicationDate, productId).orElseThrow(() ->
                new BusinessException("Price not found for brand id " + brand.getBrandId() + " and application date " + applicationDate + " with product id " + productId,
                        HttpStatus.NOT_FOUND));

        log.info("Price found with id {}", price.getPriceId());
        return priceMapper.priceEntityToResponseDto(price);
    }

}
