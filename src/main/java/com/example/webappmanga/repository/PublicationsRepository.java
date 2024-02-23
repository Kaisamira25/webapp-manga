package com.example.webappmanga.repository;

import com.example.webappmanga.dto.request.RPublicationsGenreDTO;
import com.example.webappmanga.dto.request.RPublicationsGiftDTO;
import com.example.webappmanga.model.Publications;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationsRepository extends JpaRepository<Publications, Integer> {
    Optional<Publications> findByTitle(String title);

    @Query("SELECT new com.example.webappmanga.dto.request.RPublicationsGenreDTO(p.publicationsID, g.genreID) FROM Publications p JOIN p.genres g")
    List<RPublicationsGenreDTO> findAllPublicationsGenres();

    @Query("SELECT new com.example.webappmanga.dto.request.RPublicationsGiftDTO(p.publicationsID, gift.promotionalGiftID) FROM Publications p JOIN p.gifts gift")
    List<RPublicationsGiftDTO> findAllPublicationsGifts();

}
