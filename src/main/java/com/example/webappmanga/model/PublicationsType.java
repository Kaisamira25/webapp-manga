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
@Table(name = "Publications_type")
public class PublicationsType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publications_type_id")
    private Integer publicationsTypeId;

    @Column(name = "publications_type", columnDefinition = "nvarchar(100)", nullable = false)
    private String publicationsType;

    @JsonIgnore
    @OneToMany(mappedBy = "publicationsType") // một publicationType tham chiếu nhiều Publications
    private List<Publications> listPublications;

}
