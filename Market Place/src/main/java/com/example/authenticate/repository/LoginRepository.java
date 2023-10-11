package com.example.authenticate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.authenticate.Entity.LoginEntity;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {
    public Optional<LoginEntity> findByEmailAndPass(String emailId, String pass);
}
