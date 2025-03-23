package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.service.MoviesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MoviesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MoviesService moviesService;

    @InjectMocks
    private MoviesController moviesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(moviesController).build();
    }

    @Test
    public void getMovies() throws Exception {
        Movie movie1 = new Movie(1L, "superman2", "Action", 110, 9.7, 2018);
        Movie movie2 = new Movie(2L, "lion king", "Drama", 100, 8.7, 2004);

        when(moviesService.getAllMovies()).thenReturn(List.of(movie1, movie2)); // Mocking service response

        mockMvc.perform(get("/movies/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("superman2"))
                .andExpect(jsonPath("$[1].title").value("lion king"));
    }

    @Test
    public void getMoviesShouldReturnCorrectNumberOfMovies() throws Exception {
        Movie movie1 = new Movie(1L, "superman2", "Action", 110, 9.7, 2018);
        Movie movie2 = new Movie(2L, "lion king", "Drama", 100, 8.7, 2004);

        when(moviesService.getAllMovies()).thenReturn(List.of(movie1, movie2));

        mockMvc.perform(get("/movies/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void addMovie() throws Exception {
        Movie movie = new Movie(100L, "test", "Action", 120, 9.3, 2012);

        when(moviesService.addMovie(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"test\",\"genre\":\"Action\",\"duration\":120,\"rating\":9.3,\"releaseYear\":2012}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.title").value("test"))
                .andExpect(jsonPath("$.genre").value("Action"))
                .andExpect(jsonPath("$.duration").value(120))
                .andExpect(jsonPath("$.rating").value(9.3))
                .andExpect(jsonPath("$.releaseYear").value(2012));

        verify(moviesService).addMovie(any(Movie.class));
    }

    @Test
    public void testAddMovieFailure() throws Exception {
        // Missing 'title' field
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":null,\"genre\":\"Action\",\"duration\":125,\"rating\":8.5,\"releaseYear\":2008}"))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testDeleteMovie() throws Exception {
        String movieTitle = "Superman";
        doNothing().when(moviesService).deleteMovie(movieTitle);
        mockMvc.perform(delete("/movies/{movieTitle}", movieTitle))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMovieWithPost() throws Exception {
        String movieTitle = "Superman";
        Movie movie = new Movie(1L, "Superman", "Action", 125, 8.5, 2008);
        doNothing().when(moviesService).updateMovie(movieTitle, movie);

        mockMvc.perform(post("/movies/update/{movieTitle}", movieTitle)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Superman\",\"genre\":\"Action\",\"duration\":125,\"rating\":8.5,\"releaseYear\":2008}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}


