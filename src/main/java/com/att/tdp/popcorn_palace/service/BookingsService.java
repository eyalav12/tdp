package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.BookingResponse;
import com.att.tdp.popcorn_palace.model.Booking;
import com.att.tdp.popcorn_palace.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingsService {
    private BookingsRepository bookingsRepository;
    @Autowired
    public BookingsService(BookingsRepository bookingsRepository) {
        this.bookingsRepository = bookingsRepository;
    }

    public BookingResponse createBooking(Booking booking) {
        Booking savedBooking = bookingsRepository.save(booking);
        return new BookingResponse(savedBooking.getBookingId());
    }
}
