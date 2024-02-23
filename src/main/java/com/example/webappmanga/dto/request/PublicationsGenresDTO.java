package com.example.webappmanga.dto.request;

import com.example.webappmanga.model.Genre;
import com.example.webappmanga.model.Publications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationsGenresDTO {
    private Publications publications;
    private Genre genres;
}
