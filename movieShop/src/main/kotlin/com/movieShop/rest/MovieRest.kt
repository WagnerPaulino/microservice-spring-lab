package com.movieShop.rest

import com.movieShop.dto.MovieDto
import com.movieShop.domain.MovieOrderModel
import com.movieShop.config.RabbitConfig
import com.movieShop.repository.MovieRepository
import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.amqp.rabbit.core.RabbitTemplate
import com.google.gson.Gson;
import java.time.LocalDate

@RestController
@RequestMapping("/movies")
class MovieRest(@Autowired private val movieRepository: MovieRepository, @Autowired private val rabbitTemplate: RabbitTemplate) {

    @GetMapping
    fun findAll(): List<MovieDto> {
        return movieRepository.findAll().map { movie -> movie.toDTO() }.toMutableList()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): MovieDto {
        return movieRepository.getById(id).toDTO()
    }

    @PostMapping
    fun create(@RequestBody @Valid movie: MovieDto): MovieDto {
        return movieRepository.save(movie.fromDTO()).toDTO()
    }

    @PutMapping("/{id}")
    fun upgrade(@PathVariable id: Long, @RequestBody @Valid movie: MovieDto): MovieDto {
        movie.id = id
        return movieRepository.save(movie.fromDTO()).toDTO()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        movieRepository.deleteById(id)
    }

    @PostMapping("/order/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun orderMovie(@PathVariable id: Long) {
        val rabbitConfig = RabbitConfig()
        val movie = movieRepository.getById(id)
        val movieOrder = MovieOrderModel(LocalDate.now(), movie.id)
        val gson = Gson()
        rabbitTemplate.convertAndSend(rabbitConfig.topicExchangeName, rabbitConfig.routingKeyBase+"order",gson.toJson(movieOrder))
    }
}
