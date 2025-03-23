package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;

public interface ShowtimesRepository extends JpaRepository<Showtime,Long> {
    @Query("SELECT showtime FROM Showtime showtime WHERE showtime.theater = ?1 AND showtime.endTime > ?2 AND showtime.startTime < ?3")
    List<Showtime> findAllByTheaterWithOverlap(String Theater, OffsetDateTime from, OffsetDateTime to);
}
