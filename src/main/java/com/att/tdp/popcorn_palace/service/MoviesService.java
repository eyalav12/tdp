package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.exception.ResourceNotFoundException;
import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<Movie> getAllMovies(){
        return moviesRepository.findAll();
    }

    public Movie addMovie(Movie movie){
        return moviesRepository.save(movie);
    }

    public void updateMovie(String title,Movie movie){
        moviesRepository.findByTitle(title).ifPresentOrElse(movieFound->{
            movieFound.setTitle(movie.getTitle());
            movieFound.setGenre(movie.getGenre());
            movieFound.setDuration(movie.getDuration());
            movieFound.setRating(movie.getRating());
            moviesRepository.save(movieFound);
        },

                ()->{
            throw new ResourceNotFoundException("Movie Not Found for title "+title);
                });
    }

    public void deleteMovie(String movieTitle){
//        moviesRepository.findByTitle(movieTitle).ifPresentOrElse(moviesRepository::delete,()->{
//        });
//        moviesRepository.findByTitle(movieTitle).ifPresent(moviesRepository::delete);
            moviesRepository.findByTitle(movieTitle).ifPresentOrElse(moviesRepository::delete,
                    ()->{
                        throw new ResourceNotFoundException("Movie Not Found for title "+movieTitle);
                    });
    }

}
