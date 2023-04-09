package com.moviePayment.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity class MoviePayment(
    @Id @GeneratedValue var id: Long? = null,
    @Column var price: Double? = null,
    @Column var movieId: Long? = null,
    @Column var paymentCompleted: Boolean? = null
)