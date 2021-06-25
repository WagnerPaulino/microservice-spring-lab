package com.movieShop.rest

import com.movieShop.domain.Movie
import com.movieShop.domain.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
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
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/movies")
class MovieRest(@Autowired private val movieRepository: MovieRepository) {

    @GetMapping
    fun findAll(): Flux<Movie> {
        return Flux.fromIterable(movieRepository.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Movie> {
        return Mono.just(movieRepository.getById(id))
    }

    @PostMapping
    fun create(@RequestBody movie: Movie): Mono<Movie> {
        return Mono.just(movieRepository.save(movie))
    }

    @PutMapping("/{id}")
    fun upgrade(@PathVariable id: Long, @RequestBody movie: Movie): Mono<Movie> {
        movie.id = id
        return Mono.just(movieRepository.save(movie))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        movieRepository.deleteById(id)
    }
}
