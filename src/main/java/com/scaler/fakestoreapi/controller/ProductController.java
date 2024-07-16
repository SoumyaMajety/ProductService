package com.scaler.fakestoreapi.controller;


import com.scaler.fakestoreapi.authcommon.AuthCommon;
import com.scaler.fakestoreapi.dtos.Roles;
import com.scaler.fakestoreapi.dtos.UserDto;
import com.scaler.fakestoreapi.exceptions.InvalidTokenException;
import com.scaler.fakestoreapi.exceptions.ProductNotFound;
import com.scaler.fakestoreapi.models.Product;
import com.scaler.fakestoreapi.service.ProductServie;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {


    ProductServie productServie;
    private AuthCommon authCommon;

    public ProductController(@Qualifier("SelfProductService") ProductServie productServie,
                             AuthCommon authCommon) {
        this.productServie = productServie;
        this.authCommon=authCommon;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id,@RequestHeader("token") String token ) throws InvalidTokenException,ProductNotFound {
       UserDto userDto = authCommon.validateUser(token);

       if(userDto !=null){
           for (Roles r : userDto.getRoles()){
                  if(r.getRole() == "ADMIN"){
                      Product p = productServie.getProductById(id);
                      return new ResponseEntity<>(p, HttpStatus.OK);
                  }
           }
       }
        return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    }

//    @GetMapping("/")
//    public Product[] getAllProducts() {
//
//        return productServie.getAllProducts();
//    }


// with pagenation for getting all products
    @GetMapping("/")

    public Page<Product> getAllProducts( @RequestParam("PageNumber") int pageNumber,@RequestParam("PageSize") int pageSize) {

                 return productServie.getAllProducts(pageNumber,pageSize);
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) {

        return productServie.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id") int id) throws ProductNotFound {

        return productServie.updateProduct(product, id);
    }

    @PutMapping("/{id}")
    public Product updateProductPut(@RequestBody Product product, @PathVariable("id") int id) throws ProductNotFound {
        return productServie.updateProductPut(product, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id) throws ProductNotFound {

        productServie.deleteProduct(id);
        return;
    }
}
