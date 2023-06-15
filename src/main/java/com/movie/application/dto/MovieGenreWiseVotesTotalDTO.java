package com.movie.application.dto;

import java.util.List;

public class MovieGenreWiseVotesTotalDTO {

    private String genre;

    private List<MovieGenreProjectionDTO> movies;

    private Integer totalVotes;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<MovieGenreProjectionDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieGenreProjectionDTO> movies) {
        this.movies = movies;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }
}
