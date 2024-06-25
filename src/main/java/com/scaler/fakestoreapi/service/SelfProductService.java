package com.scaler.fakestoreapi.service;

import com.scaler.fakestoreapi.exceptions.ProductNotFound;
import com.scaler.fakestoreapi.models.Category;
import com.scaler.fakestoreapi.models.Product;
import com.scaler.fakestoreapi.repo.CategoryRepository;
import com.scaler.fakestoreapi.repo.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
@Primary//(or you can use qualifier)
public class SelfProductService implements ProductServie{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Product getProductById(int id) throws ProductNotFound {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFound();
        }
        return  optionalProduct.get();
    }

    @Override
    public Product[] getAllProducts() {
        List<Product> productsList = productRepository.findAll();
        Product [] products = productsList.toArray(new Product[productsList.size()]) ;

        return products;
    }

    @Override
    public Product updateProduct(Product product, int id) throws ProductNotFound {

        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()){
            throw new ProductNotFound();
        }
        product.setId(id);
        product = productRepository.save(findCategory(product));
        return  product;
    }

    @Override
    public Product updateProductPut(Product product, int id) throws ProductNotFound {
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()){
            throw new ProductNotFound();
        }
        product.setId(id);
        product = productRepository.save(findCategory(product));
        return  product;
    }

    @Override
    public Product createProduct(Product product) {
    // create category before saving product(by calling findcategory method)
        Product saveProduct = productRepository.save(findCategory(product));
        return saveProduct;
    }

    public Product findCategory(Product product){
        String title = product.getCategory().getTitle();
        Optional<Category> optionalCategory = categoryRepository.findByTitle(title);
        if(optionalCategory.isEmpty()) {
            Category c = new Category();
            c.setTitle(title);
            Category savedCategory = categoryRepository.save(c);
            product.setCategory(savedCategory);
        }
        else{
            product.setCategory(optionalCategory.get());
        }

        return product;
    }

    @Override
    public void deleteProduct(int id) throws ProductNotFound {
      Optional<Product> p =  productRepository.findById(id);
      if (p.isEmpty()){
          throw new ProductNotFound();
      }
      productRepository.deleteById(id);
    }
}
