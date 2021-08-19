package com.movieSchedule.movieSchedule.rest

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.beans.factory.annotation.Autowired
import com.movieSchedule.movieSchedule.repository.MovieOrderRepository
import com.movieSchedule.movieSchedule.domain.MovieOrder

@RestController
@RequestMapping("/movie-orders")
class MovieOrderRest(@Autowired val movieOrderRepository: MovieOrderRepository) {

    @GetMapping
    fun findAll(): List<MovieOrder>  {
        return movieOrderRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(id: Long): MovieOrder {
        return movieOrderRepository.getById(id)
    }

}