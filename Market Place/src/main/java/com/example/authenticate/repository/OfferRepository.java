package com.example.authenticate.repository;

import com.example.authenticate.Entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<OfferEntity, String> {

    public List<OfferEntity> findBySellerId(String id);

    public List<OfferEntity> findByProductId(String id);
}
