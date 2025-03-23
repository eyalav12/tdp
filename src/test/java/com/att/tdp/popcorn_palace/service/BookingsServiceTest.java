package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.BookingResponse;
import com.att.tdp.popcorn_palace.model.Booking;
import com.att.tdp.popcorn_palace.repository.BookingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingsServiceTest {

    @Mock
    private BookingsRepository bookingsRepository;

    @InjectMocks
    private BookingsService bookingsService;

    @Test
    void createBooking() {
        Booking booking = new Booking();
        UUID bookingId = UUID.randomUUID();
        booking.setBookingId(bookingId);
        booking.setSeatNumber(10);

        when(bookingsRepository.save(booking)).thenReturn(booking);

        BookingResponse response = bookingsService.createBooking(booking);

        assertNotNull(response);
        assertEquals(bookingId, response.getBookingId());
    }
}