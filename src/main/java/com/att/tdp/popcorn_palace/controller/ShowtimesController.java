package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.model.Showtime;
import com.att.tdp.popcorn_palace.service.ShowtimesService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShowtimesController {

    private final ShowtimesService showtimesService;

    public ShowtimesController(ShowtimesService showtimesService) {
        this.showtimesService = showtimesService;
    }

    @GetMapping(path="showtimes/{showtimeId}")
    public Showtime getShowtimeById(@PathVariable Long showtimeId){
        return showtimesService.getShowtimeById(showtimeId);
    }

    @PostMapping(path="/showtimes")
    public Showtime addShowtime(@Valid @RequestBody Showtime showtime){
        return showtimesService.addShowtime(showtime);
    }

    @PostMapping(path="/showtimes/update/{showtimeId}")
    public void updateShowtimeByIdWithPost(@Valid @PathVariable Long showtimeId,@RequestBody Showtime showtime){
        showtimesService.updateShowtimeById(showtimeId,showtime);
    }

    @PutMapping(path="/showtimes/update/{showtimeId}")
    public void updateShowtimeById(@Valid @PathVariable Long showtimeId,@RequestBody Showtime showtime){
        showtimesService.updateShowtimeById(showtimeId,showtime);
    }

    @DeleteMapping(path="/showtimes/{showtimeId}")
    public void deleteShowtimeById(@PathVariable Long showtimeId){
        showtimesService.deleteShowtimeById(showtimeId);
    }

}
