package com.project.product_management.model.repository;

import com.project.product_management.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);
}
