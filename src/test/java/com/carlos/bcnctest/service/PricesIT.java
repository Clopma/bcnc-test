package com.carlos.bcnctest.service;


import org.junit.jupiter.api.Test;
import org.openapitools.model.PriceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PricesIT {

    public static final int PRODUCT_ID = 35455;
    public static final int BRAND_ID = 1;
    @Autowired
    PricesServiceImpl pricesServiceImpl;


    @Test
    void testGetPriceWhenOneValidDateReturnsThatPrice(){

        PriceResponseDto priceResponseDto = pricesServiceImpl.getPrice(PRODUCT_ID,
                OffsetDateTime.of(2020, 6, 14, 10, 0, 0, 0, ZoneOffset.UTC),
                BRAND_ID);

        assertEquals(new BigDecimal("35.50"), priceResponseDto.getPrice());
        assertEquals(1, priceResponseDto.getPriceList());
    }

    @Test
    void testGetPriceWhenSeveralValidDatesReturnsHighestPriority(){

        PriceResponseDto priceResponseDto = pricesServiceImpl.getPrice(PRODUCT_ID,
                OffsetDateTime.of(2020, 6, 14, 16, 0, 0, 0, ZoneOffset.UTC),
                BRAND_ID);

        assertEquals(new BigDecimal("25.45"), priceResponseDto.getPrice());
        assertEquals(2, priceResponseDto.getPriceList());

    }

    @Test
    void testGetPriceWhenSeveralValidStartDateAndOneValidEndDateReturnsThatPrice(){

        PriceResponseDto priceResponseDto = pricesServiceImpl.getPrice(PRODUCT_ID,
                OffsetDateTime.of(2020, 6, 14, 21, 0, 0, 0, ZoneOffset.UTC),
                BRAND_ID);

        assertEquals(new BigDecimal("35.50"), priceResponseDto.getPrice());
        assertEquals(1, priceResponseDto.getPriceList());

    }

    @Test
    void testGetPriceWhenOneValidDateReturnsThatPriceAndEndDateValidByOneHour(){

        PriceResponseDto priceResponseDto = pricesServiceImpl.getPrice(PRODUCT_ID,
                OffsetDateTime.of(2020, 6, 15, 10, 0, 0, 0, ZoneOffset.UTC),
                BRAND_ID);

        assertEquals(new BigDecimal("30.50"), priceResponseDto.getPrice());
        assertEquals(3, priceResponseDto.getPriceList());

    }

    @Test
    void testGetPriceWhenSeveralValidEndDatesDatesReturnsHighestPriority(){

        PriceResponseDto priceResponseDto = pricesServiceImpl.getPrice(PRODUCT_ID,
                OffsetDateTime.of(2020, 6, 16, 21, 0, 0, 0, ZoneOffset.UTC),
                BRAND_ID);

        assertEquals(new BigDecimal("38.95"), priceResponseDto.getPrice());
        assertEquals(4, priceResponseDto.getPriceList());

    }

}
