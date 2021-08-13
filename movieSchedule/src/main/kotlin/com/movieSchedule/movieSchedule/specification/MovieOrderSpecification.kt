package com.movieSchedule.movieSchedule.specification

import org.springframework.data.jpa.domain.Specification
import com.movieSchedule.movieSchedule.domain.MovieOrder

class MovieOrderSpecification {

    fun findByMovieId(movieId: Long?): Specification<MovieOrder> {
        return Specification{ root, _, criteriaBuilder -> criteriaBuilder.equal(root.get<Any>("movieId"), movieId)}
    }
}