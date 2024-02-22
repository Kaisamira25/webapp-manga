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
    private Integer user_id;

    @Column(name = "email",columnDefinition = "varchar(100)",nullable = false,unique = true)
    private String email;

    @Column(name = "fullname",columnDefinition = "nvarchar(100)",nullable = false)
    private String fullName;

    @JsonIgnore
    @Column(name = "password",columnDefinition = "varchar(1000)",nullable = false)
    private String password;

    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

   
    @Column(name = "is_block")
    private boolean isBlocked = false;

@JsonIgnore
    @Column(name = "refresh_token",columnDefinition = "nvarchar(1000)")
    private String refreshToken;

    @JsonIgnore
    @Column(name = "verify_email_code",columnDefinition = "varchar(255)")
    private String verificationEmailCode;

    @JsonIgnore
    @Column(name = "verify_email_code_expiration")
    private Date verificationEmailCodeExpiration;
    
    @Column(name = "is_verify")
    private boolean isVerify = false;

    @Column(name = "create_at")
    private Date createdAt;

    @JsonIgnore
    @Column(name = "refresh_password_code",columnDefinition = "nvarchar(100)")
    private String passwordRefreshToken;

    @JsonIgnore
    @Column(name = "refresh_password_code_expiration")
    private Date passwordRefreshTokenExpiration;

}
