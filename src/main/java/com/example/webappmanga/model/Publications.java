package com.example.webappmanga.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Publications")
public class Publications implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publications_id")
    private Integer publicationsID;

    @Column(name = "title", columnDefinition = "nvarchar(300)", nullable = false)
    private String title;

    @Column(name = "unit_price", columnDefinition = "float",  nullable = false)
    private Float unitPrice;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "author", columnDefinition = "nvarchar(255)", nullable = false)
    private String author;

    @Column(name = "publisher", columnDefinition = "nvarchar(255)", nullable = false)
    private String publisher;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    @Column(name = "publication_year", nullable = false)
    private Integer publicationYear;

    @Column(name = "summary", columnDefinition = "nvarchar(1000)", nullable = false)
    private String summary;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "arrival_day",nullable = false)
    private Date arrivalDay;

    @JsonIgnore
    @ManyToOne // Nhiều publications tham    chiếu đến 1 publication_type
    @JoinColumn(name = "publications_type_id")
    private PublicationsType publicationsType;

    @JsonIgnore
    @ManyToOne // Nhiều publications tham chiếu đến 1 publications_cover
    @JoinColumn(name = "publications_cover_id")
    private PublicationsCover publicationsCover;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Publications_Genre",
            joinColumns = @JoinColumn(name = "publications_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Publications_gift",
            joinColumns = @JoinColumn(name = "publications_id"),
            inverseJoinColumns = @JoinColumn(name = "promotional_gift_id")
    )
    private Set<PromotionalGift> gifts = new HashSet<>();


//    public void addGenre(Genre genres) {
//        this.genres.add(genres);
//    }
//
//    public void removeGenre(Genre genres) {
//        this.genres.remove(genres);
//        genres.getPublications().remove(this);
//    }
}

