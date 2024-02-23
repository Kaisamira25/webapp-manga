package com.example.webappmanga.repository;

import com.example.webappmanga.model.PublicationsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationsTypeRepository extends JpaRepository<PublicationsType, Integer> {
    Optional<PublicationsType> findByPublicationsType(String publicationsType);
}
