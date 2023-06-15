package com.movie.application.service;

import com.movie.application.domain.Movie;
import com.movie.application.dto.LongestDurationMoviesResponseDTO;
import com.movie.application.dto.MovieDTO;
import com.movie.application.dto.MovieGenreProjectionDTO;
import com.movie.application.dto.MovieGenreWiseVotesTotalDTO;
import com.movie.application.mapper.MovieMapper;
import com.movie.application.repository.MoviesRepository;
import com.movie.application.repository.RatingsRepository;
import com.movie.application.util.MovieGenreProjection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;

    private final RatingsRepository ratingsRepository;

    private final MovieMapper movieMapper;
    @PersistenceContext
    private final EntityManager entityManager;

    public MoviesService(MoviesRepository moviesRepository, RatingsRepository ratingsRepository, MovieMapper movieMapper, EntityManager entityManager) {
        this.moviesRepository = moviesRepository;
        this.ratingsRepository = ratingsRepository;
        this.movieMapper = movieMapper;
        this.entityManager = entityManager;
    }

    public List<LongestDurationMoviesResponseDTO> getLongestDurationMovies() {

        return moviesRepository.findTopTenByRuntimeMinutes()
                .stream()
                .map(this::convertToLongestDurationMoviesResponseDTO).toList();
    }

    public LongestDurationMoviesResponseDTO convertToLongestDurationMoviesResponseDTO(Movie movie) {

        LongestDurationMoviesResponseDTO longestDurationMoviesResponseDTO = new LongestDurationMoviesResponseDTO();
        longestDurationMoviesResponseDTO.setTconst(movie.getTconst());
        longestDurationMoviesResponseDTO.setPrimaryTitle(movie.getPrimaryTitle());
        longestDurationMoviesResponseDTO.setRuntimeMinutes(movie.getRuntimeMinutes());
        longestDurationMoviesResponseDTO.setGenres(movie.getGenres());

        return longestDurationMoviesResponseDTO;
    }

    public String saveNewMovie(MovieDTO movieDTO) {
        Movie movie = movieMapper.buildEntity(movieDTO);
        moviesRepository.save(movie);

        return "success";
    }

    public List<MovieGenreWiseVotesTotalDTO> getGenreMoviesWithSubtotals() {
        List<MovieGenreProjectionDTO> movieGenreProjectionDTOList = ratingsRepository.findMoviesByGenreWithSubtotals()
                .stream()
                .map(this::convertToMovieGenreProjectionDTO)
                .toList();

        Map<String, List<MovieGenreProjectionDTO>> genreMap = movieGenreProjectionDTOList.stream().collect(Collectors.groupingBy(MovieGenreProjectionDTO::getGenres));

        Map<String, Integer> responseMap = movieGenreProjectionDTOList.stream()
                .collect(Collectors.groupingBy(MovieGenreProjectionDTO::getGenres, Collectors.summingInt(MovieGenreProjectionDTO::getNumVotes)));

        List<MovieGenreWiseVotesTotalDTO> finalResponse = new ArrayList<>();
        responseMap.forEach((key, value) -> {
            MovieGenreWiseVotesTotalDTO dto = new MovieGenreWiseVotesTotalDTO();
            dto.setGenre(key);
            dto.setMovies(genreMap.get(key));
            dto.setTotalVotes(value);

            finalResponse.add(dto);
        });
        return finalResponse;
    }

    public MovieGenreProjectionDTO convertToMovieGenreProjectionDTO(MovieGenreProjection movieGenreProjection) {
        MovieGenreProjectionDTO movieGenreProjectionDTO = new MovieGenreProjectionDTO();
        movieGenreProjectionDTO.setGenres(movieGenreProjection.getGenres());
        movieGenreProjectionDTO.setPrimaryTitle(movieGenreProjection.getPrimaryTitle());
        movieGenreProjectionDTO.setNumVotes(movieGenreProjection.getNumVotesSubtotal());

        return movieGenreProjectionDTO;
    }

    @Transactional
    public String incrementRuntimeMinutes() {
        String query = "UPDATE Movie m SET m.runtimeMinutes = " +
                "CASE " +
                "WHEN m.genres = 'Documentary' THEN m.runtimeMinutes + 15 " +
                "WHEN m.genres = 'Animation' THEN m.runtimeMinutes + 30 " +
                "ELSE m.runtimeMinutes + 45 " +
                "END";

        Query nativeQuery = entityManager.createQuery(query);
        int updatedCount = nativeQuery.executeUpdate();

        return "Updated records: " + updatedCount;
    }

}
