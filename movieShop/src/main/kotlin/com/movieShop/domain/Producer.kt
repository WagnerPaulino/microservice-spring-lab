package com.movieShop.domain

import com.movieShop.dto.ProducerDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

@Entity
class Producer(
        @Id @GeneratedValue var id: Long? = null,
        @Column(nullable = false) var name: String? = null,
        @OneToMany(mappedBy = "producer") var movies: List<Movie>? = null
) {
    fun toDTO() = ProducerDto(id, name)
}
