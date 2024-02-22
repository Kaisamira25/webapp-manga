package com.example.webappmanga.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class AddressDTO {
	
    private String district;
    private String city;
    private String phone_number;
    private String ward;
    private String address;
    private Integer user_id;
    
}
