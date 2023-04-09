package com.movieShop.domain

import com.movieShop.dto.MovieDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Movie(
        @Id @GeneratedValue var id: Long? = null,
        @Column(nullable = false) var name: String? = null,
        @Column(nullable = false) var description: String? = null,
        @Column(nullable = true) var price: Double? = null,
        @ManyToOne
        @JoinColumn(name = "PRODUCER_ID")
        var producer: Producer? = null
) {
    fun toDTO() = MovieDto(id, name, description, price, producer = producer?.toDTO())
}
