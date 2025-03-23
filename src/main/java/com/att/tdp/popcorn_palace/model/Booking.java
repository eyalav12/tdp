package com.att.tdp.popcorn_palace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@Entity
@Table(name="bookings",uniqueConstraints = @UniqueConstraint(columnNames = {"showtimeId","seatNumber"}))
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookingId;
    @NotNull(message = "showtime id is required")
    @Positive(message = "showtime id can't be negative or zero")
    private Long showtimeId;
    @NotNull(message = "seat number is required")
    @Positive(message = "seat number can't be negative or zero")
    private Integer seatNumber;
    @NotNull(message = "user Id is required")
    private UUID userId;

    public Booking() {}

    public Booking(UUID bookingId, Long showtimeId, Integer seatNumber, UUID userId) {
        this.bookingId = bookingId;
        this.showtimeId = showtimeId;
        this.seatNumber = seatNumber;
        this.userId = userId;
    }
    public UUID getBookingId() {
        return bookingId;
    }
    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }
    public Long getShowtimeId(){
        return showtimeId;
    }
    public void setShowtimeId(Long showtimeId){
        this.showtimeId = showtimeId;
    }
    public Integer getSeatNumber(){
        return seatNumber;
    }
    public void setSeatNumber(Integer seatNumber){
        this.seatNumber = seatNumber;
    }
    public UUID getUserId(){
        return userId;
    }
    public void setUserId(UUID userId){
        this.userId = userId;
    }

}
