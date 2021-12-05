package com.moviePayment.receiver

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import com.google.gson.Gson
import com.moviePayment.domain.MovieOrder
import com.moviePayment.repository.MovieOrderRepository
import com.moviePayment.specification.MovieOrderSpecification

@Component
class ReceiverMessage(@Autowired private val movieOrderRepository: MovieOrderRepository) {

    fun receiveMessage(movieOrder: String) {
        val movieSpec = MovieOrderSpecification()
        val gson = Gson()
        val movie = gson.fromJson(movieOrder, MovieOrder().javaClass)
        movieOrderRepository.findOne(movieSpec.findByMovieId(movie.movieId)).ifPresent{movieOrderSaved -> movie.id = movieOrderSaved.id}
        movieOrderRepository.save(movie)
    }
}