package com.example.webappmanga.model;

import com.example.webappmanga.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "email",columnDefinition = "varchar(100)",nullable = false,unique = true)
    private String email;

    @Column(name = "full_name",columnDefinition = "nvarchar(100)",nullable = false)
    private String fullName;

    @JsonIgnore
    @Column(name = "password",columnDefinition = "varchar(1000)",nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(name = "is_verify")
    private boolean isVerify = false;

    @Column(name = "is_blocked")
    private boolean isBlocked = false;

    @Column(name = "remining_verification")
    private int remainingVerification = 5;

    @JsonIgnore
    @Column(name = "verification_email_code",columnDefinition = "varchar(255)")
    private String verificationEmailCode;

    @JsonIgnore
    @Column(name = "verification_email_code_expiration")
    private Date verificationEmailCodeExpiration;

    @JsonIgnore
    @Column(name = "refresh_token",columnDefinition = "nvarchar(1000)")
    private String refreshToken;

    @JsonIgnore
    @Column(name = "password_refresh_token",columnDefinition = "nvarchar(100)")
    private String passwordRefreshToken;

    @JsonIgnore
    @Column(name = "password_refresh_token_expiration")
    private Date passwordRefreshTokenExpiration;

    @Column(name = "created_at")
    private Date createdAt;
}
