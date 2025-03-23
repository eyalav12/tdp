package com.att.tdp.popcorn_palace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "movies")
public class Movie {

    public Movie() {}

    public Movie(String title,String genre, int duration,double rating, int releaseYear){
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }

    public Movie(Long id,String title,String genre, int duration,double rating, int releaseYear){
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotBlank(message = "movie title can't be empty")
    private String title;
    @NotBlank(message = "movie genre can't be empty")
    private String genre;
    @NotNull(message = "movie duration is required")
    @Positive(message = "movie duration can't be negative")
    private int duration;
    @NotNull(message = "movie rating is required")
    @PositiveOrZero(message = "movie rating can't be negative")
    private double rating;
    @Column(name="release_year")
    @Positive(message = "release year can't be negative or zero")
    private int releaseYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
