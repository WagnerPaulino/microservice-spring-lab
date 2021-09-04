package com.movieSchedule.movieSchedule.receiver

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import com.google.gson.Gson
import com.movieSchedule.movieSchedule.domain.MovieOrder
import com.movieSchedule.movieSchedule.repository.MovieOrderRepository
import com.movieSchedule.movieSchedule.specification.MovieOrderSpecification

@Component
public class ReceiverMessage(@Autowired private val movieOrderRepository: MovieOrderRepository) {

    fun receiveMessage(movieOrder: String) {
        val movieSpec = MovieOrderSpecification()
        val gson = Gson()
        val movie = gson.fromJson(movieOrder, MovieOrder().javaClass)
        movieOrderRepository.findOne(movieSpec.findByMovieId(movie.movieId)).ifPresent{movieOrderSaved -> movie.id = movieOrderSaved.id}
        movie.deliveryDate = movie.orderData?.plusDays(3)
        movieOrderRepository.save(movie)
    }
}