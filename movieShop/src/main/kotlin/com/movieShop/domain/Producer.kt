package com.movieShop.domain

import com.movieShop.dto.ProducerDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Producer(
        @Id @GeneratedValue var id: Long? = null,
        @Column(nullable = false) var name: String? = null,
        @OneToMany(mappedBy = "producer") var movies: List<Movie>? = null
) {
    fun toDTO() = ProducerDto(id, name)
}
