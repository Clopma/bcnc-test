package com.carlos.bcnctest.controller;

import org.junit.jupiter.api.Test;
import org.openapitools.model.PriceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricesControllerTest {

    public static final int PRODUCT_ID = 35455;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void priceControllerShouldReturnPrice() {
        ResponseEntity<PriceResponseDto> response =
                restTemplate.getForEntity("http://localhost:"+port+"/api/price/35455?applicationDate=2020-06-14T10:12:28Z&brandId=1", PriceResponseDto.class);

        assertTrue(HttpStatus.OK.isSameCodeAs(response.getStatusCode()));
        assertEquals(new BigDecimal("35.50"), response.getBody().getPrice());
        assertEquals(1, response.getBody().getPriceList());
    }

    @Test
    void priceControllerShouldReturn404WithBrandId2() {
        ResponseEntity<PriceResponseDto> response =
                restTemplate.getForEntity("http://localhost:"+port+"/api/price/35455?applicationDate=2020-06-14T10:12:28Z&brandId=2", PriceResponseDto.class);

        assertTrue(HttpStatus.NOT_FOUND.isSameCodeAs(response.getStatusCode()));
    }

    @Test
    void priceControllerShouldReturn400WithoutBrandIdParameter() {
        ResponseEntity<PriceResponseDto> response =
                restTemplate.getForEntity("http://localhost:"+port+"/api/price/35455?applicationDate=2020-06-14T10:12:28Z", PriceResponseDto.class);

        assertTrue(HttpStatus.BAD_REQUEST.isSameCodeAs(response.getStatusCode()));
    }


}
