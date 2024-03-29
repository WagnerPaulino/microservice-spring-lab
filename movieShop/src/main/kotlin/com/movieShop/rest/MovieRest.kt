package com.movieShop.rest

import com.movieShop.dto.MovieDto
import com.movieShop.repository.MovieRepository
import com.movieShop.service.MovieService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/movies")
class MovieRest(private val movieRepository: MovieRepository, private val movieService: MovieService) {

    @GetMapping
    fun findAll(): List<MovieDto> {
        return movieRepository.findAll().map { movie -> movie.toDTO() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): MovieDto {
        return movieRepository.getReferenceById(id).toDTO()
    }

    @PostMapping
    fun create(@RequestBody @Valid movie: MovieDto): MovieDto {
        return movieService.save(movie.fromDTO()).toDTO()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid movie: MovieDto): MovieDto {
        movieRepository.findById(id).orElseThrow{ throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Movie with id $id was not found!") }
        movie.id = id
        return movieService.save(movie.fromDTO()).toDTO()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        movieRepository.deleteById(id)
    }

    @PostMapping("/order/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun orderMovie(@PathVariable id: Long) {
        movieService.orderMovie(id)
    }
}
