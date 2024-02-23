package com.example.webappmanga.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Publications_cover")
public class PublicationsCover implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publications_cover_id")
    private Integer publicationsCoverId;

    @Column(name = "cover_type", columnDefinition = "nvarchar(100)", nullable = false)
    private String coverType;

    @JsonIgnore
    @OneToMany(mappedBy = "publicationsCover") // một bookCover tham chiếu nhiều MangaAndLightNovels
    private List<Publications> listPublications;

}
