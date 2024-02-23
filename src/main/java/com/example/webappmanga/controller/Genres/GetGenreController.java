package com.example.webappmanga.controller.Genres;

import com.example.webappmanga.dto.response.Message;


import com.example.webappmanga.model.Genre;
import com.example.webappmanga.service.serviceInterface.IGenre;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genre")
@RequiredArgsConstructor
public class GetGenreController {

    private final IGenre iGenre;

    @GetMapping("/all")
    @Operation(summary = "Find All Genre",responses = {
            @ApiResponse(description = "success", responseCode = "200")})
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Message> getAllListGenre() {
        List<Genre> list = iGenre.getAllGenre();
        return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", list));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a Genre with the ID",responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "Genre not found", responseCode = "404")})
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Message> getGenreId(@PathVariable("id") Integer id) {
        Genre genres = iGenre.getByGenreId(id);
        if (genres != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", genres));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, " Genre dose not exist"));

    }

    @Operation(summary = "Find a Genre with the given name.",responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "Genre not found", responseCode = "404")})
    @GetMapping("/search/{name}")
    public ResponseEntity<Message> getGenreByName(@PathVariable("name") String genresName) {

        Genre genres = iGenre.findByGenre(genresName).orElse(null);
        if (genres != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, " successfully", genres));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(0, "Genre dose not exist"));
    }
}