package com.moviePayment.receiver

import com.google.gson.Gson
import com.moviePayment.domain.MoviePayment
import com.moviePayment.repository.MoviePaymentRepository
import com.moviePayment.service.MoviePaymentService
import com.moviePayment.specification.MoviePaymentSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ReceiverMessage(
        @Autowired private val movieOrderRepository: MoviePaymentRepository,
        @Autowired private val moviePaymentService: MoviePaymentService
) {

    fun receiveMessage(movieOrder: String) {
        val movieSpec = MoviePaymentSpecification()
        val gson = Gson()
        val movie = gson.fromJson(movieOrder, MoviePayment().javaClass)
        movieOrderRepository.findOne(movieSpec.findByMovieId(movie.movieId)).ifPresent {
                movieOrderSaved ->
            movie.id = movieOrderSaved.id
        }
        movieOrderRepository.save(movie)
        moviePaymentService.processPayment(movie)
    }
}
