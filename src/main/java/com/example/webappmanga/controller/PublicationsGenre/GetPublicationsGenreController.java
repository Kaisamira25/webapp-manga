package com.example.webappmanga.controller.PublicationsGenre;

import com.example.webappmanga.dto.request.RPublicationsGenreDTO;
import com.example.webappmanga.dto.response.Message;

import com.example.webappmanga.service.PublicationsGenreService;

import com.example.webappmanga.service.serviceInterface.IPublicationsGenre;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/publications-genre")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class GetPublicationsGenreController {

//    private final PublicationsGenreService publicationsGenreService;

    private final IPublicationsGenre iPublicationsGenre;

    // Get All
    @Operation(summary = "Get all PublicationsGenres", responses = {
            @ApiResponse(description = "success", responseCode = "200")})
    @GetMapping("/all")
    public ResponseEntity<Message> getAllPublicationsGenres() {
        List<RPublicationsGenreDTO> list = iPublicationsGenre.getAllPublicationsGenres();
        return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", list));
    }
}


