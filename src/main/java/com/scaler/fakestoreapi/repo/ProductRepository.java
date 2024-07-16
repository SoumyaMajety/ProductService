package com.scaler.fakestoreapi.repo;

import com.scaler.fakestoreapi.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findById(Integer id);
    @Override
    Product save(Product p);

    List<Product> findAll();
    void deleteById(Integer id);
    Page<Product> findAll(Pageable pageable);
}
