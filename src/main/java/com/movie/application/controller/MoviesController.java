package com.movie.application.controller;

import com.movie.application.domain.Movie;
import com.movie.application.dto.FilterDto;
import com.movie.application.dto.LongestDurationMoviesResponseDTO;
import com.movie.application.dto.MovieDTO;
import com.movie.application.dto.MovieGenreWiseVotesTotalDTO;
import com.movie.application.service.JdbcTemplateService;
import com.movie.application.service.MoviesService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/api/v1")
@RequestMapping
@RestController
@Tag(name = "Movie Controller")
public class MoviesController {

    private final MoviesService moviesService;

    private final JdbcTemplateService jdbcTemplateService;

    public MoviesController(MoviesService moviesService, JdbcTemplateService jdbcTemplateService) {
        this.moviesService = moviesService;
        this.jdbcTemplateService = jdbcTemplateService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok().body(jdbcTemplateService.findAll());

    }

    @GetMapping("/movies_by_filter")
    public ResponseEntity<List<Movie>> findAll(@RequestBody FilterDto filterDto) {
        return ResponseEntity.ok().body(jdbcTemplateService.findAll(filterDto));

    }


    @ApiResponse(description = "This API is Dummy API")
    @GetMapping
    public ResponseEntity<List<LongestDurationMoviesResponseDTO>> dummy() {

        return ResponseEntity.ok().body(moviesService.getLongestDurationMovies());
    }

    @ApiResponse(description = "This API will fetch top ten movies with longest duration in minutes")
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
