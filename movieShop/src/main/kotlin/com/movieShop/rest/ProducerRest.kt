package com.movieShop.rest

import com.movieShop.dto.ProducerDto
import com.movieShop.repository.ProducerRepository
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
@RequestMapping("/producers")
class ProducerRest(private val producerRepository: ProducerRepository) {

    @GetMapping
    fun findAll(): List<ProducerDto> {
        return producerRepository.findAll().map { producer -> producer.toDTO() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ProducerDto {
        return producerRepository.getReferenceById(id).toDTO()
    }

    @PostMapping
    fun create(@RequestBody @Valid producer: ProducerDto): ProducerDto {
        return producerRepository.save(producer.fromDTO()).toDTO()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid producer: ProducerDto): ProducerDto {
        producerRepository.findById(id).orElseThrow{ throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Producer with id $id was not found!") }
        producer.id = id
        return producerRepository.save(producer.fromDTO()).toDTO()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        producerRepository.deleteById(id)
    }
}
