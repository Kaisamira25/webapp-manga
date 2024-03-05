package com.example.webappmanga.controller.PublicationType;

import com.example.webappmanga.dto.response.Message;
import com.example.webappmanga.model.PublicationsType;
import com.example.webappmanga.service.serviceInterface.IPublicationsType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/type")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PublicationsTypeController {

    private final IPublicationsType iType;

    private final ModelMapper modelMapper;

    @Operation(summary = "Create a PublicationsType", responses = {
            @ApiResponse(description = "success", responseCode = "200"),})
    @PostMapping()
    public ResponseEntity<Message> createType(@RequestBody PublicationsType typeDTO) {
        PublicationsType type = modelMapper.map(typeDTO, PublicationsType.class);
        type = (PublicationsType) iType.createType(type);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Message(1, "successful", type));
    }

    @Operation(summary = "Update a PublicationsType", description = "When the PublicationsType is successfully updated, the response status code is 200; otherwise, it is 400, accompanied by a corresponding message.", responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PublicationsType not found", responseCode = "400")})
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateType(@PathVariable("id") Integer id, @RequestBody PublicationsType typeDTO) {
        PublicationsType type = modelMapper.map(typeDTO, PublicationsType.class);
        type = iType.updateType(id, type);
        if (type != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "updated successfully", type));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "updated fail, PublicationsType dose not exist"));

    }

    @Operation(summary = "Delete a PublicationsType", description = "When the PublicationsType is successfully updated, the response status code is 200; otherwise, it is 400, accompanied by a corresponding message.", responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PublicationsType not found", responseCode = "400")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteType(@PathVariable("id") Integer id) {

        boolean status = iType.deleteType(id);
        if (status) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "deleted successfully"));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "deleted fail, PublicationsType dose not exist"));

    }
}
