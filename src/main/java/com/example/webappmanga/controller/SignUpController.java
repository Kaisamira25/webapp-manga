package com.example.webappmanga.controller;

import com.example.webappmanga.dto.request.SignUpDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SignUpController {
    @Operation(description = "User register",summary = "Register",responses = {
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Email already exist",responseCode = "302"),
            @ApiResponse(description = "Password or email format is not correct",responseCode = "400")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpDTO signUpDTO){
        return ResponseEntity.status(HttpStatus.OK).body("Noice");
    }
}
