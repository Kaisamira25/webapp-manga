package com.example.webappmanga.service.serviceInterface;

import com.example.webappmanga.dto.request.RPublicationsGenreDTO;
import com.example.webappmanga.model.Publications;

import java.util.List;

public interface IPublicationsGenre {
    Publications addPublicationsToGenre(RPublicationsGenreDTO publicationsGenreDTO);
    boolean removePublicationsFromGenre(RPublicationsGenreDTO publicationsGenreDTO);
    List<RPublicationsGenreDTO> getAllPublicationsGenres();
}

