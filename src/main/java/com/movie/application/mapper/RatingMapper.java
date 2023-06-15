package com.movie.application.mapper;

import com.movie.application.domain.Rating;
import com.movie.application.dto.RatingDTO;
import org.springframework.stereotype.Service;

@Service
public class RatingMapper {

    public Rating buildEntity(RatingDTO ratingDTO) {

        Rating rating = new Rating();
        rating.setTconst(ratingDTO.getTconst());
        rating.setAverageRating(ratingDTO.getAverageRating());
        rating.setNumVotes(ratingDTO.getNumVotes());

        return rating;
    }

    public RatingDTO buildDTO(Rating rating) {

        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setTconst(rating.getTconst());
        ratingDTO.setAverageRating(rating.getAverageRating());
        ratingDTO.setNumVotes(rating.getNumVotes());

        return ratingDTO;
    }

}
