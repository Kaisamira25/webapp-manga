package com.example.webappmanga.controller.Publications;

import com.example.webappmanga.dto.response.Message;
import com.example.webappmanga.model.Publications;
import com.example.webappmanga.service.serviceInterface.IPublications;
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
@RequestMapping("/api/v1/publications")
@RequiredArgsConstructor
public class GetPublicationsController {

    private final IPublications iPublications;

    @GetMapping("/all")
    @Operation(summary = "Find All Publications",responses = {
            @ApiResponse(description = "success", responseCode = "200")})
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Message> getAllListPublications() {
        List<Publications> list = iPublications.getAllPublications();
        return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", list));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a Publications with the ID",responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "Publications not found", responseCode = "404")})
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Message> getPublicationsId(@PathVariable("id") Integer id) {
        Publications manga = iPublications.getByPublicationsId(id);
        if (manga != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", manga));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, " Publications dose not exist"));

    }

    @Operation(summary = "Find a Publications with the given name.",responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "Publications not found", responseCode = "404")})
    @GetMapping("/search/{name}")
    public ResponseEntity<Message> getByPublicationsTitle(@PathVariable("name") String title) {

        Publications publications = iPublications.findByTitle(title).orElse(null);
        if (publications != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, " successfully", publications));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(0, "Publications dose not exist"));
    }
}
