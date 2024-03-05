package com.example.webappmanga.controller.PublicationsCover;

import com.example.webappmanga.dto.response.Message;
import com.example.webappmanga.model.PublicationsCover;
import com.example.webappmanga.service.serviceInterface.IPublicationsCover;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/cover")
@RequiredArgsConstructor
public class GetPublicationsCoverController {

    private final IPublicationsCover iCover;

    @GetMapping("/all")
    @Operation(summary = "Find All PublicationsCover",responses = {
            @ApiResponse(description = "success", responseCode = "200")})
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Message> getAllListBookCover() {
        List<PublicationsCover> list = iCover.getAllCover();
        return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", list));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a PublicationsCover with the ID",responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PublicationsCover not found", responseCode = "404")})
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Message> getByCoverId(@PathVariable("id") Integer id) {
        PublicationsCover cover = iCover.getByCoverId(id);
        if (cover != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", cover));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, " PublicationsCover dose not exist"));

    }

    @Operation(summary = "Find a PublicationsCover with the given name.",responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PublicationsCover not found", responseCode = "404")})
    @GetMapping("/search/{name}")
    public ResponseEntity<Message> getByCoverTitle(@PathVariable("name") String coverTitle) {

        PublicationsCover cover = iCover.findByCoverType(coverTitle).orElse(null);
        if (cover != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, " successfully", cover));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(0, "category dose not exist"));
    }
}
