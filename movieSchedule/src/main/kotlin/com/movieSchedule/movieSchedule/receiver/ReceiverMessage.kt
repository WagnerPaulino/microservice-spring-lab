package com.movieSchedule.movieSchedule.receiver

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import com.google.gson.Gson
import com.movieSchedule.movieSchedule.domain.MovieOrder
import com.movieSchedule.movieSchedule.service.MovieShopApiService
import com.movieSchedule.movieSchedule.repository.MovieOrderRepository
import com.movieSchedule.movieSchedule.specification.MovieOrderSpecification

@Component
class ReceiverMessage(@Autowired private val movieOrderRepository: MovieOrderRepository, @Autowired private val movieShopApiService: MovieShopApiService) {

    fun receiveMessage(movieOrder: String) {
        val movieSpec = MovieOrderSpecification()
        val gson = Gson()
        val movie = gson.fromJson(movieOrder, MovieOrder().javaClass)
        val movieShopModel = movieShopApiService.getMovieById(movie.movieId!!)
        if(movieShopModel != null) {
            movieOrderRepository.findOne(movieSpec.findByMovieId(movie.movieId)).ifPresent{movieOrderSaved -> movie.id = movieOrderSaved.id}
            movie.deliveryDate = movie.orderData?.plusDays(3)
            movieOrderRepository.save(movie)
        }
    }
}