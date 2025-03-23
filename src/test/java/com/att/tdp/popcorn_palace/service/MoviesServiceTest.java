package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.exception.ResourceNotFoundException;
import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.repository.MoviesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MoviesServiceTest {

    @Mock
    private MoviesRepository moviesRepository;

    @InjectMocks
    private MoviesService moviesService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllMovies() {
        Movie movie1 = new Movie("Movie 1", "Action", 120, 8.5,2020);
        Movie movie2 = new Movie("Movie 2", "Drama", 100, 7.0,2019);
        when(moviesRepository.findAll()).thenReturn(List.of(movie1, movie2));

        List<Movie> result = moviesService.getAllMovies();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Movie 1", result.get(0).getTitle());
        assertEquals("Movie 2", result.get(1).getTitle());
    }

    @Test
    void addMovie() {
        Movie movie = new Movie("Movie test", "Drama", 130, 8.0,2010);
        when(moviesRepository.save(movie)).thenReturn(movie);

        Movie result = moviesService.addMovie(movie);

        assertNotNull(result);
        assertEquals("Movie test", result.getTitle());
        assertEquals("Drama", result.getGenre());
    }

    @Test
    void updateMovie() {
        String movieTitle = "Inception";
        Movie existingMovie = new Movie(movieTitle, "Sci-Fi", 150, 8.8, 2010);
        Movie updatedMovie = new Movie(movieTitle, "Sci-Fi", 150, 9.0, 2010);  // Updated details

        when(moviesRepository.findByTitle(movieTitle)).thenReturn(Optional.of(existingMovie));

        moviesService.updateMovie(movieTitle, updatedMovie);

        assertEquals("Sci-Fi", existingMovie.getGenre());
        assertEquals(150, existingMovie.getDuration());
        assertEquals(9.0, existingMovie.getRating(), 0.2);
        verify(moviesRepository, times(1)).save(existingMovie);
    }

    @Test
    public void testUpdateMovieShouldThrowExceptionWhenMovieNotFound() {
        String movieTitle = "NonExistentMovie";
        Movie updatedMovie = new Movie(movieTitle, "Action", 120, 8.0,1998);

        when(moviesRepository.findByTitle(movieTitle)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            moviesService.updateMovie(movieTitle, updatedMovie);
        });
    }

    @Test
    void deleteMovie() {
        String movieTitle = "Shrek";
        Movie existingMovie = new Movie(movieTitle, "Comedy", 148, 8.8,1990);

        when(moviesRepository.findByTitle(movieTitle)).thenReturn(Optional.of(existingMovie));
        moviesService.deleteMovie(movieTitle);
        verify(moviesRepository, times(1)).delete(existingMovie);
    }
}