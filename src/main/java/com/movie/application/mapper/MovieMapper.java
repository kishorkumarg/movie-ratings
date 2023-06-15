package com.movie.application.mapper;

import com.movie.application.domain.Movie;
import com.movie.application.dto.MovieDTO;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {

    public Movie buildEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTconst(movieDTO.getTconst());
        movie.setTitleType(movieDTO.getTitleType());
        movie.setPrimaryTitle(movieDTO.getPrimaryTitle());
        movie.setRuntimeMinutes(movieDTO.getRuntimeMinutes());
        movie.setGenres(movieDTO.getGenres());

        return movie;
    }

    public MovieDTO buildDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTconst(movie.getTconst());
        movieDTO.setTitleType(movie.getTitleType());
        movieDTO.setPrimaryTitle(movie.getPrimaryTitle());
        movieDTO.setRuntimeMinutes(movie.getRuntimeMinutes());
        movieDTO.setGenres(movie.getGenres());

        return movieDTO;
    }

}
