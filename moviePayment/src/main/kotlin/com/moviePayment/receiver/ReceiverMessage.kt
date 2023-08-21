package com.moviePayment.receiver

import com.google.gson.Gson
import com.moviePayment.domain.MoviePayment
import com.moviePayment.service.MovieShopApiService
import com.moviePayment.repository.MoviePaymentRepository
import com.moviePayment.service.MoviePaymentService
import com.moviePayment.specification.MoviePaymentSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ReceiverMessage(
        private val movieOrderRepository: MoviePaymentRepository,
        private val moviePaymentService: MoviePaymentService,
        private val movieShopApiService: MovieShopApiService
) {

    fun receiveMessage(movieOrder: String) {
        val movieSpec = MoviePaymentSpecification()
        val gson = Gson()
        val movie = gson.fromJson(movieOrder, MoviePayment().javaClass)
        val movieShopModel = movieShopApiService.getMovieById(movie.movieId!!)
        if(movieShopModel != null) {
            movieOrderRepository.findOne(movieSpec.findByMovieId(movie.movieId)).ifPresent {
                    movieOrderSaved ->
                movie.id = movieOrderSaved.id
            }
            movieOrderRepository.save(movie)
            moviePaymentService.processPayment(movie)
        }
    }
}
