package com.movieSchedule.movieSchedule.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class MovieOrder(
    @Id @GeneratedValue var id: Long? = null,
    @Column var orderData: LocalDate? = null,
    @Column var deliveryDate: LocalDate? = null,
    @Column var movieId: Long? = null
)