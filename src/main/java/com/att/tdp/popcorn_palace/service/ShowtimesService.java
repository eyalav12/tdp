package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.exception.ResourceNotFoundException;
import com.att.tdp.popcorn_palace.exception.ShowtimeOverlappingException;
import com.att.tdp.popcorn_palace.model.Showtime;
import com.att.tdp.popcorn_palace.repository.ShowtimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimesService {

    private final ShowtimesRepository showtimesRepository;

    @Autowired
    public ShowtimesService(ShowtimesRepository showtimesRepository) {
        this.showtimesRepository = showtimesRepository;
    }

    public Showtime getShowtimeById(Long showtimeId) {
        return showtimesRepository.findById(showtimeId).orElseThrow(()-> new ResourceNotFoundException("Showtime not found for id " + showtimeId));
    }


    public Showtime addShowtime(Showtime showtime){
        //verify not adding overlapping showtimes on same theater
        List<Showtime> allByTheaterWithOverLap = showtimesRepository.findAllByTheaterWithOverlap(showtime.getTheater(), showtime.getStartTime(), showtime.getEndTime());
        if(allByTheaterWithOverLap.isEmpty()){
            return showtimesRepository.save(showtime);
        }
        else{
            //overlapping times of two showtimes
            throw new ShowtimeOverlappingException("Showtime overlapping with an existing showtime in same theater.");
        }
    }


    public void updateShowtimeById(Long showtimeId,Showtime showtime){
        showtimesRepository.findById(showtimeId).ifPresentOrElse((showtimeFound)->{
            //verify here also that overlapping not allowed
            List<Showtime> allByTheaterWithOverlap = showtimesRepository.findAllByTheaterWithOverlap(showtime.getTheater(), showtime.getStartTime(), showtime.getEndTime());
            allByTheaterWithOverlap.removeIf(s->s.getId().equals(showtimeId));
            for (Showtime s: allByTheaterWithOverlap){
                System.out.println(s.getId()+" "+s.getStartTime()+"->"+s.getEndTime());
            }
            System.out.println(allByTheaterWithOverlap);
            if(allByTheaterWithOverlap.isEmpty()){
                showtimeFound.setMovieId(showtime.getMovieId());
                showtimeFound.setTheater(showtime.getTheater());
                showtimeFound.setStartTime(showtime.getStartTime());
                showtimeFound.setEndTime(showtime.getEndTime());
                showtimeFound.setPrice(showtime.getPrice());
                showtimesRepository.save(showtimeFound);
            }
            else{
                throw new ShowtimeOverlappingException("Showtime overlapping with an existing showtime in same theater.");
            }
        },()->{
            //not found the showtime with this id
            throw new ResourceNotFoundException("Showtime not found for id "+ showtimeId);
        });

    }

    public void deleteShowtimeById(Long showtimeId){
        showtimesRepository.findById(showtimeId).ifPresentOrElse(showtimesRepository::delete, ()->{
            throw new ResourceNotFoundException("Showtime not found for id "+showtimeId);
        });
    }
}
