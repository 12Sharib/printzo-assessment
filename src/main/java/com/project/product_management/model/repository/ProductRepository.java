package com.project.product_management.model.repository;

import com.project.product_management.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  boolean existsByName(String name);
}
