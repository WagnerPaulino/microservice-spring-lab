package com.movieShop.dto

import com.movieShop.domain.Producer

data class ProducerDto(
        var id: Long? = null,
        var name: String? = null
) {
    fun fromDTO() = Producer(id, name)
}
