package com.carlos.bcnctest.exception;

import lombok.extern.log4j.Log4j2;
import org.openapitools.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneOffset;
import java.util.Date;

@ControllerAdvice
@Log4j2
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<ErrorDto> handleBusinessException(BusinessException ex) {
        log.error("Returning error: " + ex.getMessage());
        return new ResponseEntity<>(new ErrorDto()
                .errorMessage(ex.getMessage())
                .timestamp(new Date().toInstant().atOffset(ZoneOffset.UTC)),
                ex.httpStatus);
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<ErrorDto> handleException(RuntimeException ex) {
        log.error("Returning error: " + ex.getMessage());
        return new ResponseEntity<>(new ErrorDto()
                .errorMessage(ex.getMessage())
                .timestamp(new Date().toInstant().atOffset(ZoneOffset.UTC)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
