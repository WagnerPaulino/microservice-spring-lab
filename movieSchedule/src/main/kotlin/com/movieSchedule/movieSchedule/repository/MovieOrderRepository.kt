package com.movieSchedule.movieSchedule.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import com.movieSchedule.movieSchedule.domain.MovieOrder
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

@Repository interface MovieOrderRepository : JpaRepository<MovieOrder, Long>, JpaSpecificationExecutor<MovieOrder>