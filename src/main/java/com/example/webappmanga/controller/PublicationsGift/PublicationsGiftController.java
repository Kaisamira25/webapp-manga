package com.example.webappmanga.controller.PublicationsGift;

import com.example.webappmanga.dto.request.RPublicationsGiftDTO;
import com.example.webappmanga.dto.response.Message;
import com.example.webappmanga.model.Publications;
import com.example.webappmanga.repository.PromotionalGiftRepository;
import com.example.webappmanga.repository.PublicationsRepository;
import com.example.webappmanga.service.PublicationsGiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/publications-gift")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PublicationsGiftController {

    private final PublicationsGiftService publicationsGiftService;

    private final PublicationsRepository publicationsRepo;

    private final PromotionalGiftRepository promotionalGiftRepo;

    private final ModelMapper modelMapper;

    // Create
    @Operation(summary = "Add a Gift to a publications", responses = {
            @ApiResponse(description = "success", responseCode = "200"),})
    @PostMapping("/add")
    public ResponseEntity<Message> addPublicationsToGift(@RequestBody RPublicationsGiftDTO publicationsGiftDTO) {
        Publications publications = publicationsGiftService.addPublicationsToGift(publicationsGiftDTO);
        if (publications != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(1, "Gift added successfully to the publications", publications));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "Publications or Gift not found"));
    }

    // Delete
    @Operation(summary = "Remove a Gift from a publications", responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "Publications or Gift not found", responseCode = "400")})
    @DeleteMapping("/remove")
    public ResponseEntity<Message> removePublicationsFromGift(@RequestBody RPublicationsGiftDTO publicationsGiftDTO) {
        boolean isRemoved = publicationsGiftService.removePublicationsFromGift(publicationsGiftDTO);
        if (isRemoved) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Genre with ID: " + publicationsGiftDTO.promotionalGiftID() + " removed successfully from the publications with ID: " + publicationsGiftDTO.publicationsID()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "Publications or Gift not found"));
        }
    }
}

