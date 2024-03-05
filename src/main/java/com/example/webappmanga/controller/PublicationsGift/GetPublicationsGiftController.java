package com.example.webappmanga.controller.PublicationsGift;

import com.example.webappmanga.dto.request.RPublicationsGenreDTO;
import com.example.webappmanga.dto.request.RPublicationsGiftDTO;
import com.example.webappmanga.dto.response.Message;
import com.example.webappmanga.service.serviceInterface.IPublicationsGenre;
import com.example.webappmanga.service.serviceInterface.IPublicationsGift;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/publications-gift")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class GetPublicationsGiftController {

    private final IPublicationsGift iPublicationsGift;

    // Get All
    @Operation(summary = "Get all PublicationsGifts", responses = {
            @ApiResponse(description = "success", responseCode = "200")})
    @GetMapping("/all")
    public ResponseEntity<Message> getAllPublicationsGifts() {
        List<RPublicationsGiftDTO> list = iPublicationsGift.getAllPublicationsGifts();
        return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", list));
    }
}


