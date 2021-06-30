package com.movieShop.domain

import com.movieShop.dto.MovieDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

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
