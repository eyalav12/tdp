package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.model.Showtime;
import com.att.tdp.popcorn_palace.service.ShowtimesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.OffsetDateTime;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ShowtimesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ShowtimesService showtimesService;

    @InjectMocks
    private ShowtimesController showtimesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(showtimesController).build();
    }

    @Test
    void getShowtimeById() throws Exception {
        Long showtimeId = 1L;
        Showtime showtime = new Showtime(1L, 1L, "Theater 1", OffsetDateTime.now(), OffsetDateTime.now().plusHours(2), 10.0);

        when(showtimesService.getShowtimeById(showtimeId)).thenReturn(showtime);

        mockMvc.perform(get("/showtimes/{showtimeId}", showtimeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(showtimeId))
                .andExpect(jsonPath("$.movieId").value(1L))
                .andExpect(jsonPath("$.theater").value("Theater 1"))
                .andExpect(jsonPath("$.startTime").exists())
                .andExpect(jsonPath("$.endTime").exists())
                .andExpect(jsonPath("$.price").value(10.0));
    }

    @Test
    void addShowtime()throws Exception {
        Showtime validShowtime = new Showtime(null, 1L, "Theater 1", OffsetDateTime.now(), OffsetDateTime.now().plusHours(2), 10.0);

        when(showtimesService.addShowtime(any(Showtime.class))).thenReturn(new Showtime(1L, 1L, "Theater 1", OffsetDateTime.now(), OffsetDateTime.now().plusHours(2), 10.0));

        mockMvc.perform(post("/showtimes")
                        .contentType("application/json")
                        .content("{\"movieId\":1,\"theater\":\"Theater 1\",\"startTime\":\"2023-08-15T20:00:00+00:00\",\"endTime\":\"2023-08-15T22:00:00+00:00\",\"price\":10.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.movieId").value(1L))
                .andExpect(jsonPath("$.theater").value("Theater 1"))
                .andExpect(jsonPath("$.startTime").exists())
                .andExpect(jsonPath("$.endTime").exists())
                .andExpect(jsonPath("$.price").value(10.0));
    }

    @Test
    void addShowtimeWithInvalidMovieId() throws Exception {
        //invalid field value of movie id
        mockMvc.perform(post("/showtimes")
                        .contentType("application/json")
                        .content("{\"movieId\":-1,\"theater\":\"Theater 1\",\"startTime\":\"2023-08-15T20:00:00+00:00\",\"endTime\":\"2023-08-15T22:00:00+00:00\",\"price\":10.0}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShowtimeByIdWithPost() throws Exception{
        Long showtimeId = 1L;
        Showtime updatedShowtime = new Showtime(showtimeId, 2L, "Theater 1", OffsetDateTime.now(), OffsetDateTime.now().plusHours(2), 15.0);

        doNothing().when(showtimesService).updateShowtimeById(showtimeId, updatedShowtime);

        mockMvc.perform(post("/showtimes/update/{showtimeId}", showtimeId)
                        .contentType("application/json")
                        .content("{\"movieId\":2,\"theater\":\"Theater 1\",\"startTime\":\"2023-08-15T20:00:00+00:00\",\"endTime\":\"2023-08-15T22:00:00+00:00\",\"price\":15.0}"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void deleteShowtimeById()throws Exception {
        Long showtimeId = 1L;

        doNothing().when(showtimesService).deleteShowtimeById(showtimeId);

        mockMvc.perform(delete("/showtimes/{showtimeId}", showtimeId))
                .andExpect(status().isOk());
    }
}