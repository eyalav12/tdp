package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.dto.BookingResponse;
import com.att.tdp.popcorn_palace.model.Booking;
import com.att.tdp.popcorn_palace.service.BookingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookingsController {

    private BookingsService bookingsService;
    @Autowired
    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping(path = "/bookings")
    public BookingResponse createBooking(@Valid @RequestBody Booking booking) {
        return bookingsService.createBooking(booking);
    }
}
