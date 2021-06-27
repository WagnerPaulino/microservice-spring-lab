package com.movieShop.rest

import com.movieShop.dto.ProducerDto
import com.movieShop.repository.ProducerRepository
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
@RequestMapping("/producers")
class ProducerRest(@Autowired private val producerRepository: ProducerRepository) {

    @GetMapping
    fun findAll(): Flux<ProducerDto> {
        return Flux.fromIterable(
                producerRepository.findAll().map { producer -> producer.toDTO() }.toMutableList())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<ProducerDto> {
        return Mono.just(producerRepository.getById(id).toDTO())
    }

    @PostMapping
    fun create(@RequestBody producer: ProducerDto): Mono<ProducerDto> {
        return Mono.just(producerRepository.save(producer.fromDTO()).toDTO())
    }

    @PutMapping("/{id}")
    fun upgrade(@PathVariable id: Long, @RequestBody producer: ProducerDto): Mono<ProducerDto> {
        producer.id = id
        return Mono.just(producerRepository.save(producer.fromDTO()).toDTO())
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        producerRepository.deleteById(id)
    }
}