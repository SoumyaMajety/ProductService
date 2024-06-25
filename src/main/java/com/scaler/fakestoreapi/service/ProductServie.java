package com.scaler.fakestoreapi.service;

import com.scaler.fakestoreapi.exceptions.ProductNotFound;
import com.scaler.fakestoreapi.models.Product;

import java.util.List;

public interface ProductServie {
    public Product getProductById(int id) throws ProductNotFound;
    public  Product[] getAllProducts();
    public Product updateProduct(Product product, int id) throws ProductNotFound;
    public Product updateProductPut(Product product,int id) throws ProductNotFound;
    public Product createProduct(Product product);
    public void deleteProduct(int id) throws ProductNotFound;

}
