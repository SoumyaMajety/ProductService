package com.scaler.fakestoreapi.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ProductNotFoundExceptionResponseDto {
    String message;
    HttpStatus httpStatus;

}
