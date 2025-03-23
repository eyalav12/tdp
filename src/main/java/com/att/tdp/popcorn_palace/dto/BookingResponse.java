package com.att.tdp.popcorn_palace.dto;

import java.util.UUID;

public class BookingResponse {
    private UUID bookingId;

    public BookingResponse(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getBookingId() {
        return bookingId;
    }
}
