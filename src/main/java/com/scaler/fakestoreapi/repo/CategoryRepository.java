package com.scaler.fakestoreapi.repo;

import com.scaler.fakestoreapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category save(Category c);
    Optional<Category> findByTitle(String title);
    Optional<Category>findById(Integer id);
}
