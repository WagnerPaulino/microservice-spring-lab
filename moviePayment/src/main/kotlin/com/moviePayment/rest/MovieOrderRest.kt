package com.moviePayment.rest

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.beans.factory.annotation.Autowired
import com.moviePayment.repository.MovieOrderRepository
import com.moviePayment.domain.MovieOrder
import com.moviePayment.specification.MovieOrderSpecification
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/movie-payments")
class MovieOrderRest(@Autowired val movieOrderRepository: MovieOrderRepository) {

    @GetMapping("/{id}")
    fun findById(id: Long): MovieOrder {
        val spec = MovieOrderSpecification()
        return movieOrderRepository.findOne(spec.findByMovieId(id)).orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id $id was not found!")}
    }

}