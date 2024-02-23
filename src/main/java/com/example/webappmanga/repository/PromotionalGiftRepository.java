package com.example.webappmanga.repository;

import com.example.webappmanga.dto.request.RPublicationsGenreDTO;
import com.example.webappmanga.dto.request.RPublicationsGiftDTO;
import com.example.webappmanga.model.PromotionalGift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionalGiftRepository extends JpaRepository<PromotionalGift, Integer> {
    Optional<PromotionalGift> findByPromotionalGiftName(String giftName);

}
