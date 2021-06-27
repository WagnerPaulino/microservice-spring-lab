package com.movieShop.repository

import com.movieShop.domain.Producer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ProducerRepository : JpaRepository<Producer, Long>, JpaSpecificationExecutor<Producer>
