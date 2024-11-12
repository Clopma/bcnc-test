package com.carlos.bcnctest.controller;

import com.carlos.bcnctest.service.PricesService;
import lombok.extern.log4j.Log4j2;
import org.openapitools.api.PriceApi;
import org.openapitools.model.PriceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.OffsetDateTime;

@Controller
@Log4j2
public class PricesController implements PriceApi {

    @Autowired
    PricesService pricesService;

    public ResponseEntity<PriceResponseDto> getPricesInformation(Integer productId, OffsetDateTime applicationDate, Integer brandId) {

        log.info("Entering get price controller for product id {}, applicationDate {} and brandId {}", productId, applicationDate, brandId);

        ResponseEntity<PriceResponseDto> response = new ResponseEntity<>(pricesService.getPrice(productId, applicationDate, brandId), HttpStatus.OK);

        log.info("Exiting get price controller with status {}", response.getStatusCode());
        return response;

    }


}
