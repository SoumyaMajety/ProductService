package com.scaler.fakestoreapi.controller;

import com.scaler.fakestoreapi.service.ProductServie;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductControllerTest {

    @Test
    void testGetProductById() {

        assertThrows(RuntimeException.class,()->abc());
    }

    int abc(){
        throw new RuntimeException();
    }
}