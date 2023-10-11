package com.example.authenticate.repository;

import com.example.authenticate.Entity.LoginEntity;
import com.example.authenticate.Entity.ProductEntity;
import com.example.authenticate.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
