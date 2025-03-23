package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.service.MoviesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoviesController {
    private final MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping(path="/movies/all")
    public List<Movie> getMovies(){
        return moviesService.getAllMovies();
    }

    @PostMapping(path = "/movies")
    public Movie addMovie(@Valid @RequestBody Movie movie) {
        return moviesService.addMovie(movie);
    }

    /*in the example the updated is written with Post,
        so adding a post end point for update, for the safe side
     */
    @PostMapping(path="/movies/update/{movieTitle}")
    public void updateMovieWithPost(@Valid @RequestBody Movie movie,@PathVariable("movieTitle") String movieTitle) {
        moviesService.updateMovie(movieTitle,movie);
    }

    @PutMapping(path="/movies/update/{movieTitle}")
    public void updateMovie(@RequestBody Movie movie,@PathVariable("movieTitle") String movieTitle) {
        moviesService.updateMovie(movieTitle,movie);
    }

    @DeleteMapping(path="/movies/{movieTitle}")
    public void deleteMovie(@PathVariable("movieTitle") String movieTitle) {
        moviesService.deleteMovie(movieTitle);
    }

}
