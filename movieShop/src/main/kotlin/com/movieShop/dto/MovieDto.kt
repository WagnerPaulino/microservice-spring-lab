package com.movieShop.dto

import com.movieShop.domain.Movie

class MovieDto(
        var id: Long? = null,
        var name: String? = null,
        var description: String? = null,
        var producer: ProducerDto? = null
) {

    fun fromDTO() = Movie(id, name, description, producer = producer?.fromDTO())
}
