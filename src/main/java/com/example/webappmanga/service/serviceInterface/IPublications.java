package com.example.webappmanga.service.serviceInterface;

import com.example.webappmanga.model.Publications;

import java.util.List;
import java.util.Optional;

public interface IPublications {
    Publications createPublications(Publications publicationsDTO);
    Publications updatePublications(Integer id, Publications publicationsDTO);
    boolean deletePublications(Integer id);

    Optional<Publications> findByTitle(String title);
    Publications getByPublicationsId(Integer id);
    List<Publications> getAllPublications();

}
