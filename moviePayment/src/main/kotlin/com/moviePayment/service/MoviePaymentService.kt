package com.moviePayment.service

import com.moviePayment.domain.MoviePayment
import com.moviePayment.repository.MoviePaymentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MoviePaymentService(private val movieOrderRepository: MoviePaymentRepository) {

    fun processPayment(movieOrder: MoviePayment) {
        movieOrder.paymentCompleted = true
        movieOrderRepository.save(movieOrder)
    }
}
