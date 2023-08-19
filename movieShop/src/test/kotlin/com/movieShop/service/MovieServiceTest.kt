package com.movieShop.service

import com.movieShop.domain.Movie
import com.movieShop.repository.MovieRepository
import com.movieShop.repository.ProducerRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.amqp.rabbit.core.RabbitTemplate
import java.util.Optional

@ExtendWith(MockitoExtension::class)
internal class MovieServiceTest {

    private lateinit var movieService: MovieService
    @Mock
    private lateinit var movieRepository: MovieRepository
    @Mock
    private lateinit var rabbitTemplate: RabbitTemplate
    @Mock
    private lateinit var producerRepository: ProducerRepository
    @Captor
    private lateinit var orderPayload: ArgumentCaptor<String>
    private val movieId: Long = 1L
    private val expectedOrderString = "{\"price\":30.0,\"movieId\":1,\"paymentCompleted\":false}"

    @BeforeEach
    fun setUp() {
        val movie = Movie(movieId, "titanic", "romance", 30.0, null)
        Mockito.`when`(movieRepository.findById(movieId)).thenReturn(Optional.of(movie))
        movieService = MovieService(movieRepository, producerRepository, rabbitTemplate)
    }

    @Test
    fun shouldPublishMessageWhenOrderMovie() {
        movieService.orderMovie(movieId)
        Mockito.verify(rabbitTemplate, Mockito.times(2)).convertAndSend(Mockito.anyString(), Mockito.anyString(), orderPayload.capture())
        val orderString = orderPayload.value
        assertNotNull(orderString)
        assertEquals(expectedOrderString, orderString)
    }
}