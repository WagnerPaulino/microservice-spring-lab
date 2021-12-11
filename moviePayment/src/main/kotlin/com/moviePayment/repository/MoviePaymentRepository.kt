package com.moviePayment.repository


import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import com.moviePayment.domain.MoviePayment
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

@Repository interface MoviePaymentRepository : JpaRepository<MoviePayment, Long>, JpaSpecificationExecutor<MoviePayment>