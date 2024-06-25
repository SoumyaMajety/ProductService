package com.scaler.fakestoreapi;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestClass {

    @Test
   public void  add(){
        int i=1;
        int j=1;
        int z= i+j;

         assertEquals(2,z);
    }
}
