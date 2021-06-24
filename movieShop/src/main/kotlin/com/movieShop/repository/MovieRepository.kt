package com.movieShop.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie>