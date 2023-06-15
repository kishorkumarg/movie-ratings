package com.movie.application.service;

import com.movie.application.domain.Rating;
import com.movie.application.dto.TopRatedMoviesResponseDTO;
import com.movie.application.repository.RatingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {

    private final RatingsRepository ratingsRepository;

    public RatingsService(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    public List<TopRatedMoviesResponseDTO> getTopRatedMovies() {

        return ratingsRepository.findTopRatedMovies()
                .stream()
                .map(this::convertTOTopRatedMoviesResponseDTO)
                .toList();
    }

    public TopRatedMoviesResponseDTO convertTOTopRatedMoviesResponseDTO(Rating rating) {
        TopRatedMoviesResponseDTO topRatedMoviesResponseDTO = new TopRatedMoviesResponseDTO();
        topRatedMoviesResponseDTO.setTconst(rating.getTconst());
        topRatedMoviesResponseDTO.setPrimaryTitle(rating.getMovie().getPrimaryTitle());
        topRatedMoviesResponseDTO.setGenres(rating.getMovie().getGenres());
        topRatedMoviesResponseDTO.setAverageRating(rating.getAverageRating());

        return topRatedMoviesResponseDTO;
    }
}
