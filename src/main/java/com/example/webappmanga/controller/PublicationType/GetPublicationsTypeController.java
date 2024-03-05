package com.example.webappmanga.controller.PublicationType;

import com.example.webappmanga.dto.response.Message;

import com.example.webappmanga.model.PublicationsType;
import com.example.webappmanga.service.serviceInterface.IPublicationsType;
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
@RequestMapping("/api/v1/type")
@RequiredArgsConstructor
public class GetPublicationsTypeController {

    private final IPublicationsType iType;

    @GetMapping("/all")
    @Operation(summary = "Find All PublicationsType",responses = {
            @ApiResponse(description = "success", responseCode = "200")})
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Message> getAllListType() {
        List<PublicationsType> list = iType.getAllType();
        return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", list));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a PublicationsType with the ID",responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PublicationsType not found", responseCode = "404")})
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Message> getByTypeId(@PathVariable("id") Integer id) {
        PublicationsType type = iType.getByTypeId(id);
        if (type != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "Successfully", type));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, " PublicationsType dose not exist"));

    }

    @Operation(summary = "Find a PublicationsType with the given name.",responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PublicationsType not found", responseCode = "404")})
    @GetMapping("/search/{name}")
    public ResponseEntity<Message> getType(@PathVariable("name") String publicationsType) {

        PublicationsType type = iType.findByType(publicationsType).orElse(null);
        if (type != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, " successfully", type));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(0, "category dose not exist"));
    }
}
