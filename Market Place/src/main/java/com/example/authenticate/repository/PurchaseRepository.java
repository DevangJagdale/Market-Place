package com.example.authenticate.repository;

import com.example.authenticate.Entity.PurchasedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchasedEntity, String> {
    public List<PurchasedEntity> findByBuyerId(String emailId);
}
