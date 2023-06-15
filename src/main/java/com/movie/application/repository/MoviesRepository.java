package com.movie.application.repository;

import com.movie.application.domain.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie, String> {

    @Query("SELECT m FROM Movie m ORDER BY m.runtimeMinutes DESC LIMIT 10")
    List<Movie> findTopTenByRuntimeMinutes();

    @Transactional
    @Query(value = "UPDATE Movie m SET m.runtimeMinutes = " +
            "CASE " +
            "WHEN m.genres = 'Documentary' THEN m.runtimeMinutes + 15 " +
            "WHEN m.genres = 'Animation' THEN m.runtimeMinutes + 30 " +
            "ELSE m.runtimeMinutes + 45 " +
            "END", nativeQuery = true)
    void incrementRuntimeMinutes();
}
