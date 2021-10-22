package com.movieShop.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import com.movieShop.repository.MovieRepository
import com.movieShop.repository.ProducerRepository
import com.movieShop.config.RabbitConfig
import com.movieShop.domain.MovieOrderModel
import com.movieShop.domain.Movie
import java.time.LocalDate
import com.google.gson.Gson
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

@Service
class MovieService(@Autowired private val movieRepository: MovieRepository,
                @Autowired private val producerRepository: ProducerRepository,
                @Autowired private val rabbitTemplate: RabbitTemplate) {

    fun orderMovie(id: Long) {
        val rabbitConfig = RabbitConfig()
        val movie = movieRepository.findById(id).orElseThrow{ throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Movie with id $id was not found!") }
        val movieOrder = MovieOrderModel(LocalDate.now(), movie.id)
        val gson = Gson()
        rabbitTemplate.convertAndSend(rabbitConfig.topicExchangeName, rabbitConfig.routingKeyBase+"order",gson.toJson(movieOrder))
    }

    fun save(movie: Movie): Movie {
        producerRepository.findById(movie.producer?.id!!)
            .orElseThrow{ throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Producer with id ${movie.producer?.id} was not found!") }
        return movieRepository.save(movie)
    }
}