package com.moviePayment.specification

import org.springframework.data.jpa.domain.Specification
import com.moviePayment.domain.MoviePayment

class MoviePaymentSpecification
 {

    fun findByMovieId(movieId: Long?): Specification<MoviePayment> {
        return Specification{ root, _, criteriaBuilder -> criteriaBuilder.equal(root.get<Any>("movieId"), movieId)}
    }
}