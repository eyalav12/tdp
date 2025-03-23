package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.dto.BookingResponse;
import com.att.tdp.popcorn_palace.model.Booking;
import com.att.tdp.popcorn_palace.service.BookingsService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class BookingsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookingsService bookingsService;

    @InjectMocks
    private BookingsController bookingsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingsController).build();
    }

    @Test
    public void createBooking() throws Exception {
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking(bookingId, 1L, 15, UUID.fromString("84438967-f68f-4fa0-b620-0f08217e76af"));

        BookingResponse bookingResponse = new BookingResponse(bookingId);
        when(bookingsService.createBooking(any(Booking.class))).thenReturn(bookingResponse);

        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"showtimeId\": 1, \"seatNumber\": 15, \"userId\": \"84438967-f68f-4fa0-b620-0f08217e76af\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(bookingId.toString()));
    }

    @Test
    public void createBooking_WhenSeatNumberIsMissing() throws Exception {
        String jsonRequest = "{\"showtimeId\": 1, \"userId\": \"84438967-f68f-4fa0-b620-0f08217e76af\"}";

        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest()) ;
    }
}