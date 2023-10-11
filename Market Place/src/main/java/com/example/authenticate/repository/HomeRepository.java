package com.example.authenticate.repository;

import com.example.authenticate.Entity.SignupEntity;
import com.example.authenticate.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<UserEntity, String> {
}
