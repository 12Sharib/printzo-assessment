package com.project.product_management.model.repository;

import com.project.product_management.model.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

  boolean existsByName(String name);
}
