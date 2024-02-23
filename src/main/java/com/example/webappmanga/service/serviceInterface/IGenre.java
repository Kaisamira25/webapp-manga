package com.example.webappmanga.service.serviceInterface;


import com.example.webappmanga.model.Genre;

import java.util.List;
import java.util.Optional;

public interface IGenre {

    Genre createGenre(Genre genreDTO);
    Genre updateGenre(Integer id, Genre genresDTO);
    boolean deleteGenre(Integer id);

    Optional<Genre> findByGenre(String genreName);
    Genre getByGenreId(Integer id);
    List<Genre> getAllGenre();
}
