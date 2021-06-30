package com.movieShop.dto

import com.movieShop.domain.Movie
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class MovieDto(
        var id: Long? = null,
        @field:NotEmpty("Field name can't be empty")
        @field:NotBlank("Field name can't be blank")
        @field:NotNull("Field name can't be null")
        var name: String? = null,
        @field:NotEmpty("Field description can't be empty")
        @field:NotBlank("Field description can't be blank")
        @field:NotNull("Field description can't be null")
        var description: String? = null,
        @field:NotNull("Field price can't be null")
        var price: Double? = null,
        var producer: ProducerDto? = null
) {

    fun fromDTO() = Movie(id, name, description, price, producer = producer?.fromDTO())
}
