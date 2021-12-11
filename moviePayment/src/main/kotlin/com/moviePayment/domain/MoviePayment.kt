package com.moviePayment.domain

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Entity

@Entity class MoviePayment(
    @Id @GeneratedValue var id: Long? = null,
    @Column var price: Double? = null,
    @Column var movieId: Long? = null,
    @Column var paymentCompleted: Boolean? = null
)