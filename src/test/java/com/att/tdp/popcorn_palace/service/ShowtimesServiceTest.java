package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.exception.ResourceNotFoundException;
import com.att.tdp.popcorn_palace.model.Showtime;
import com.att.tdp.popcorn_palace.repository.ShowtimesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.time.OffsetDateTime;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShowtimesServiceTest {

    @Mock
    private ShowtimesRepository showtimesRepository;

    @InjectMocks
    private ShowtimesService showtimesService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getShowtimeById() {
        Showtime showtime = new Showtime();
        showtime.setId(1L);
        when(showtimesRepository.findById(1L)).thenReturn(Optional.of(showtime));

        Showtime result = showtimesService.getShowtimeById(1L);
        assertEquals(1L, result.getId());
        assertNotNull(result);
    }

    @Test
    void addShowtime() {
        Showtime showtime = new Showtime();
        doReturn(showtime)
                .when(showtimesRepository)
                .save(any(Showtime.class));

        Showtime addedShowtime = showtimesService.addShowtime(showtime);

        assertNotNull(addedShowtime);
    }

    @Test
    void updateShowtimeById() {
        Long showtimeId = 1L;
        Showtime updatedShowtime = new Showtime();
        updatedShowtime.setMovieId(1L);
        updatedShowtime.setTheater("Updated Theater");
        updatedShowtime.setStartTime(OffsetDateTime.now().plusHours(4));
        updatedShowtime.setEndTime(OffsetDateTime.now().plusHours(5));
        updatedShowtime.setPrice(12.0);

        // Mocking the repository behavior (not finding the showtime)
        when(showtimesRepository.findById(showtimeId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            showtimesService.updateShowtimeById(showtimeId, updatedShowtime);
        });
    }

    @Test
    void deleteShowtimeById() {
        Showtime showtime = new Showtime();
        showtime.setId(1L);
        when(showtimesRepository.findById(1L)).thenReturn(Optional.of(showtime));
        showtimesService.deleteShowtimeById(1L);
    }
}