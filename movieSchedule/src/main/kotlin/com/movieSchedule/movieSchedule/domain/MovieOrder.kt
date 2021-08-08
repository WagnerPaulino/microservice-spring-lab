package com.movieSchedule.movieSchedule.domain

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Column
import java.time.LocalDate
import javax.persistence.Entity

@Entity
class MovieOrder(
    @Id @GeneratedValue var id: Long? = null,
    @Column var orderData: LocalDate? = null,
    @Column var movieId: Long? = null
)