package com.scaler.fakestoreapi.ControllerAdvice;


import com.scaler.fakestoreapi.dtos.ProductNotFoundExceptionResponseDto;
import com.scaler.fakestoreapi.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

        @ExceptionHandler(ProductNotFound.class)
        public ResponseEntity<ProductNotFoundExceptionResponseDto> handleProductNotFoundEception(){
                ProductNotFoundExceptionResponseDto responseDto = new ProductNotFoundExceptionResponseDto();
                responseDto.setMessage("Product not found..");
                responseDto.setHttpStatus(HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);

        }
}
