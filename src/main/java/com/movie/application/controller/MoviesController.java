package com.movie.application.controller;

import com.movie.application.dto.LongestDurationMoviesResponseDTO;
import com.movie.application.dto.MovieDTO;
import com.movie.application.dto.MovieGenreWiseVotesTotalDTO;
import com.movie.application.service.MoviesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping("/longest-duration-movies")
    public ResponseEntity<List<LongestDurationMoviesResponseDTO>> findLongestDurationMovies() {

        return ResponseEntity.ok().body(moviesService.getLongestDurationMovies());
    }

    @PostMapping("/new-movie")
    public ResponseEntity<String> saveNewMovie(@RequestBody MovieDTO movieDTO) {

        return ResponseEntity.ok().body(moviesService.saveNewMovie(movieDTO));
    }

    @GetMapping("/genre-movies-with-subtotals")
    public ResponseEntity<List<MovieGenreWiseVotesTotalDTO>> findGenreMoviesWithSubtotals() {

        return ResponseEntity.ok().body(moviesService.getGenreMoviesWithSubtotals());
    }

    @PostMapping("/update-runtime-minutes")
    public ResponseEntity<String> updateRuntimeMinutes() {

        return ResponseEntity.ok().body(moviesService.incrementRuntimeMinutes());
    }

}
