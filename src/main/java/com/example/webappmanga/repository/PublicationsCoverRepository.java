package com.example.webappmanga.repository;

import com.example.webappmanga.model.PublicationsCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationsCoverRepository extends JpaRepository<PublicationsCover, Integer> {
    Optional<PublicationsCover> findByCoverType(String coverType);
}
