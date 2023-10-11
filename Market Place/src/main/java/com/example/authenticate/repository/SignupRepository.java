package com.example.authenticate.repository;

import com.example.authenticate.Entity.LoginEntity;
import com.example.authenticate.Entity.SignupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignupRepository extends JpaRepository<SignupEntity, String> {
}
