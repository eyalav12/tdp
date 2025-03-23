package com.att.tdp.popcorn_palace.model;

import com.att.tdp.popcorn_palace.validation.ShowtimeStartAndEndLimit;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;


@Entity
@Table(name="showtimes")
@ShowtimeStartAndEndLimit()
public class Showtime {
    public Showtime(Long movieId, String theater, OffsetDateTime startTime, OffsetDateTime endTime, Double price) {
        this.movieId = movieId;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Showtime(Long id, Long movieId, String theater, OffsetDateTime startTime, OffsetDateTime endTime, Double price) {
        this.id = id;
        this.movieId = movieId;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Showtime(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "movie id can't be negative or zero")
    @NotNull(message = "Movie Id is required")
    @Column(name="movie")
    private Long movieId;
    @NotBlank(message = "Theater name can't be empty")
    private String theater;
    @Column(name="start_time")
    @NotNull(message = "start time is required")
    private OffsetDateTime startTime;
    @Column(name="end_time")
    @NotNull(message = "end time is required")
    private OffsetDateTime endTime;
    @NotNull(message = "movie price is required")
    @Positive(message = "price must be greater than 0")
    private Double price;

    public Long getMovieId(){
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTheater() {
        return theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
