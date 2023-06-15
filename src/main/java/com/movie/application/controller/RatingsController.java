package com.movie.application.controller;

import com.movie.application.dto.TopRatedMoviesResponseDTO;
import com.movie.application.service.MoviesService;
import com.movie.application.service.RatingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class RatingsController {

    private final RatingsService ratingsService;

    private final MoviesService moviesService;

    public RatingsController(RatingsService ratingsService, MoviesService moviesService) {
        this.ratingsService = ratingsService;
        this.moviesService = moviesService;
    }

    @GetMapping("/top-rated-movies")
    public ResponseEntity<List<TopRatedMoviesResponseDTO>> findTopRatedMovies() {

        return ResponseEntity.ok().body(ratingsService.getTopRatedMovies());
    }
}
