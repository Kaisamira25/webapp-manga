package com.example.webappmanga.controller;

import com.example.webappmanga.dto.request.SignUpDTO;
import com.example.webappmanga.dto.response.Message;
import com.example.webappmanga.service.SignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    private final HttpServletRequest request;
    @Operation(description = "User register",summary = "Register",responses = {
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Email already exist",responseCode = "302"),
            @ApiResponse(description = "Password or email format is not correct",responseCode = "400")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpDTO signUpDTO, HttpServletRequest request){
        int status = signUpService.register(signUpDTO, request);
        if (status == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message(0,"Email or password format is incorrect"));
        }else if (status == 1){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Message(1,"Register successfully",signUpDTO));
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new Message(2,"Email already exist"));
    }
    public String applicationUrl(HttpServletRequest request){
        return "https://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


    // OTP
    @Operation(description = "User verify",summary = "Verify",responses = {
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Verification failed",responseCode = "400")
    })
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String otp, HttpServletRequest request) {
        // Lấy email từ session hoặc cookie
        String email = (String) request.getSession().getAttribute("email");

        boolean isVerified = signUpService.verify(email, otp);
        if (isVerified) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Message(1,"Verification successful"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message(0,"Verification failed"));
        }
    }

}
