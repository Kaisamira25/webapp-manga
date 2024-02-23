package com.example.webappmanga.controller.PublicationsGenre;

import com.example.webappmanga.dto.request.PublicationsDTO;
import com.example.webappmanga.dto.request.RPublicationsGenreDTO;
import com.example.webappmanga.dto.response.Message;
import com.example.webappmanga.model.Genre;
import com.example.webappmanga.model.Publications;
import com.example.webappmanga.repository.GenreRepository;
import com.example.webappmanga.repository.PublicationsRepository;
import com.example.webappmanga.service.PublicationsGenreService;
import com.example.webappmanga.service.PublicationsService;
import com.example.webappmanga.service.serviceInterface.IPublications;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/publications-genre")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PublicationsGenreController {

    private final PublicationsGenreService publicationsGenreService;

    private final PublicationsRepository publicationsRepo;

    private final GenreRepository genreRepo;

    private final ModelMapper modelMapper;

    // Create
    @Operation(summary = "Add a genre to a publications", responses = {
            @ApiResponse(description = "success", responseCode = "200"),})
    @PostMapping("/add")
    public ResponseEntity<Message> addPublicationsToGenre(@RequestBody RPublicationsGenreDTO publicationsGenreDTO) {
        Publications publications = publicationsGenreService.addPublicationsToGenre(publicationsGenreDTO);
        if (publications != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(1, "Genre added successfully to the publications", publications));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "Publications or Genre not found"));
    }

    // Delete
    @Operation(summary = "Remove a genre from a publications", responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "Publications or Genre not found", responseCode = "400")})
    @DeleteMapping("/remove")
    public ResponseEntity<Message> removePublicationsFromGenre(@RequestBody RPublicationsGenreDTO publicationsGenreDTO) {
        boolean isRemoved = publicationsGenreService.removePublicationsFromGenre(publicationsGenreDTO);
        if (isRemoved) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Genre with ID: " + publicationsGenreDTO.genreID() + " removed successfully from the publications with ID: " + publicationsGenreDTO.publicationsID()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "Publications or Genre not found"));
        }
    }
}

