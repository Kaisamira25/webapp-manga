package com.example.webappmanga.controller.PromotinalGift;

import com.example.webappmanga.dto.request.PromotionalGiftDTO;
import com.example.webappmanga.dto.response.Message;

import com.example.webappmanga.model.PromotionalGift;
import com.example.webappmanga.service.serviceInterface.IPromotionalGift;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/gift")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PromotionalGiftController {

    private final IPromotionalGift iGift;

    private final ModelMapper modelMapper;

    @Operation(summary = "PromotionalGift a Genres", responses = {
            @ApiResponse(description = "success", responseCode = "200"),})
    @PostMapping()
    public ResponseEntity<Message> createGift(@RequestBody PromotionalGiftDTO giftDTO) {
        PromotionalGift gift = modelMapper.map(giftDTO, PromotionalGift.class);
        gift = (PromotionalGift) iGift.createGift(gift);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Message(1, "successful", gift));
    }

    @Operation(summary = "Update a PromotionalGift", description = "When the PromotionalGift is successfully updated, the response status code is 200; otherwise, it is 400, accompanied by a corresponding message.", responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PromotionalGift not found", responseCode = "400")})
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateGift(@PathVariable("id") Integer id, @RequestBody PromotionalGiftDTO giftDTO) {
        PromotionalGift gift = modelMapper.map(giftDTO, PromotionalGift.class);
        gift = iGift.updateGift(id, gift);
        if (gift != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "updated successfully", gift));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "updated fail, PromotionalGift dose not exist"));

    }

    @Operation(summary = "Delete a PromotionalGift", description = "When the PromotionalGift is successfully updated, the response status code is 200; otherwise, it is 400, accompanied by a corresponding message.", responses = {
            @ApiResponse(description = "success", responseCode = "200"),
            @ApiResponse(description = "PromotionalGift not found", responseCode = "400")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteGift(@PathVariable("id") Integer id) {

        boolean status = iGift.deleteGift(id);
        if (status) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "deleted successfully"));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "deleted fail, Genres dose not exist"));

    }
}
