package com.movieShop.service

import com.google.gson.GsonBuilder
import com.movieShop.config.RabbitConfig
import com.movieShop.converters.LocalDateAdapter
import com.movieShop.domain.Movie
import com.movieShop.domain.MovieOrderModel
import com.movieShop.domain.MoviePaymentModel
import com.movieShop.repository.MovieRepository
import com.movieShop.repository.ProducerRepository
import java.time.LocalDate
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MovieService(
                private val movieRepository: MovieRepository,
                private val producerRepository: ProducerRepository,
                private val rabbitTemplate: RabbitTemplate
) {

        fun orderMovie(id: Long) {
                val rabbitConfig = RabbitConfig()
                val movie =
                                movieRepository.findById(id).orElseThrow {
                                        throw ResponseStatusException(
                                                        HttpStatus.INTERNAL_SERVER_ERROR,
                                                        "Movie with id $id was not found!"
                                        )
                                }
                val movieOrder = MovieOrderModel(LocalDate.now(), movie.id)
                val gson = GsonBuilder()
                    .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
                    .create()
                rabbitTemplate.convertAndSend(
                                rabbitConfig.topicExchangeNameSchedule,
                                rabbitConfig.routingKeyBase + "order",
                                gson.toJson(movieOrder)
                )
                val moviePayment = MoviePaymentModel(movie.price, movie.id, false)
                rabbitTemplate.convertAndSend(
                                rabbitConfig.topicExchangeNamePayment,
                                rabbitConfig.routingKeyBase + "order",
                                gson.toJson(moviePayment)
                )
        }

        fun save(movie: Movie): Movie {
                producerRepository.findById(movie.producer?.id!!).orElseThrow {
                        throw ResponseStatusException(
                                        HttpStatus.INTERNAL_SERVER_ERROR,
                                        "Producer with id ${movie.producer?.id} was not found!"
                        )
                }
                return movieRepository.save(movie)
        }
}
