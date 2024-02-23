package com.example.webappmanga.controller.PublicationsCover;


import com.example.webappmanga.dto.response.Message;

import com.example.webappmanga.model.PublicationsCover;

import com.example.webappmanga.service.serviceInterface.IPublicationsCover;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cover")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PublicationsCoverController {

    private final IPublicationsCover iCover;

    private final ModelMapper modelMapper;

    @Operation(summary = "Create a PublicationsCover", responses = {
            @ApiResponse(description = "success", responseCode = "200"),})
    @PostMapping()
    public ResponseEntity<Message> createCover(@RequestBody PublicationsCover coverDTO) {
        PublicationsCover cover = modelMapper.map(coverDTO, PublicationsCover.class);
        cover = (PublicationsCover) iCover.createCover(cover);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Message(1, "successful", cover));
    }

    @Operation(summary = "Update a PublicationsCover", description = "When the PublicationsCover is successfully updated, the response status code is 200; otherwise, it is 400, accompanied by a corresponding message.", responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PublicationsCover not found", responseCode = "400")})
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateCover(@PathVariable("id") Integer id, @RequestBody PublicationsCover coverDTO) {
        PublicationsCover cover = modelMapper.map(coverDTO, PublicationsCover.class);
        cover = iCover.updateCover(id, cover);
        if (cover != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "updated successfully", cover));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "updated fail, bookCoverDTO dose not exist"));

    }

    @Operation(summary = "Delete a PublicationsCover", description = "When the PublicationsCover is successfully updated, the response status code is 200; otherwise, it is 400, accompanied by a corresponding message.", responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PublicationsCover not found", responseCode = "400")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteCover(@PathVariable("id") Integer id) {

        boolean status = iCover.deleteCover(id);
        if (status) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "deleted successfully"));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "deleted fail, PublicationsCover dose not exist"));

    }
}
