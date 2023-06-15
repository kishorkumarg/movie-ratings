package com.movie.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MovieGenreProjectionDTO {

    @JsonIgnore
    private String genres;

    private String primaryTitle;

    private Integer numVotes;

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public Integer getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(Integer numVotes) {
        this.numVotes = numVotes;
    }
}
