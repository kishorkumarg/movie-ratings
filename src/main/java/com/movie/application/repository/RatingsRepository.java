package com.movie.application.repository;

import com.movie.application.domain.Rating;
import com.movie.application.util.MovieGenreProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Rating, String> {

    @Query("SELECT r FROM Rating r WHERE r.averageRating > 6.0 ORDER BY r.averageRating DESC LIMIT 10")
    List<Rating> findTopRatedMovies();

    @Query("SELECT m.genres AS genres, m.primaryTitle AS primaryTitle, SUM(r.numVotes) AS numVotesSubtotal " +
            "FROM Movie m " +
            "JOIN Rating r ON m.tconst = r.tconst " +
            "GROUP BY m.genres, m.primaryTitle")
    List<MovieGenreProjection> findMoviesByGenreWithSubtotals();
}
