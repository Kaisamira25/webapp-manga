package com.example.webappmanga.dto.request;


import com.example.webappmanga.model.PublicationsCover;
import com.example.webappmanga.model.PublicationsType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationsDTO {
    private String title;
    private Float unitPrice;
    private Integer stock;
    private String author;
    private String publisher;
    private Integer publicationYear;
    private String summary;
    private Date arrivalDay;
    private PublicationsCover publicationsCover;
    private PublicationsType publicationsType;
//    @JsonIgnore
//    private List<GenreDTO> genres;
}
