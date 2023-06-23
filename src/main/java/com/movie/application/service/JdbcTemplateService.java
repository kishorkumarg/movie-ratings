package com.movie.application.service;

import com.movie.application.domain.Movie;
import com.movie.application.dto.FilterDto;
import com.movie.application.mapper.MovieRowMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcTemplateService {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> findAll() {
        String sqlQuery = "SELECT * from movie";
        return jdbcTemplate.query(sqlQuery, new MovieRowMapper());
    }

    public List<Movie> findAll(FilterDto filterDto) {
        StringBuilder sqlQuery = new StringBuilder("SELECT * from movie WHERE 1 = 1 ");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotBlank(filterDto.getTconst())) {
            sqlQuery.append("AND tconst = ? ");
            params.add(filterDto.getTconst());
        }
        if (StringUtils.isNotBlank(filterDto.getTitleType())) {
            sqlQuery.append("AND title_type = ? ");
            params.add(filterDto.getTitleType());
        }
        if (StringUtils.isNotBlank(filterDto.getGenres())) {
            sqlQuery.append("AND genres = ? ");
            params.add(filterDto.getGenres());
        }
        if (filterDto.getRuntimeMinutes() != null) {
            sqlQuery.append("AND runtime_minutes = ? ");
            params.add(filterDto.getRuntimeMinutes());
        }
        return jdbcTemplate.query(sqlQuery.toString(), new MovieRowMapper(), params.toArray());
    }
}
