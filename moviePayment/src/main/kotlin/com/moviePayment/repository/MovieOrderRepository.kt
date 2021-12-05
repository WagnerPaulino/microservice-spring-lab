package com.moviePayment.repository


import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import com.moviePayment.domain.MovieOrder
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

@Repository interface MovieOrderRepository : JpaRepository<MovieOrder, Long>, JpaSpecificationExecutor<MovieOrder>