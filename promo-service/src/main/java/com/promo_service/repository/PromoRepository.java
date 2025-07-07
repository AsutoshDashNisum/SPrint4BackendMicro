package com.promo_service.repository;

import com.promo_service.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoRepository extends JpaRepository<Promo, String> {
    List<Promo> findByStatus(String status);
}