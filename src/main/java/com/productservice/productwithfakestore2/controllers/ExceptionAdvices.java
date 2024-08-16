package com.productservice.productwithfakestore2.controllers;

import com.productservice.productwithfakestore2.dtos.ErrorResponseDto;
import com.productservice.productwithfakestore2.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlingException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    // can write this too
//    public ResponseEntity<String> handlingException(Exception e){
//        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
//        errorResponseDto.setErrorMessage(e.getMessage());
//        return new ResponseEntity<>("PHAT GAYA", HttpStatus.NOT_FOUND);
//    }
}
