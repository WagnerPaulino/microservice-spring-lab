package com.moviePayment.rest

import com.moviePayment.domain.MoviePayment
import com.moviePayment.repository.MoviePaymentRepository
import com.moviePayment.specification.MoviePaymentSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/movie-payments")
class MovieOrderRest(val movieOrderRepository: MoviePaymentRepository) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): MoviePayment {
        val spec = MoviePaymentSpecification()
        return movieOrderRepository.findOne(spec.findByMovieId(id)).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id $id was not found!")
        }
    }
}
