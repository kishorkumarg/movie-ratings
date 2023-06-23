package com.movie.application.mapper;

import com.movie.application.domain.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setTconst(rs.getString("tconst"));
        movie.setTitleType(rs.getString("title_type"));
        movie.setPrimaryTitle(rs.getString("primary_title"));
        movie.setRuntimeMinutes(rs.getInt("runtime_minutes"));
        movie.setGenres(rs.getString("genres"));
        return movie;
    }
}
