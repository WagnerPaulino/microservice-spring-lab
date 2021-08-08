package com.movieShop.dto

import com.movieShop.domain.Movie
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class MovieDto(
        var id: Long? = null,
        @field:NotEmpty(message = "Field name can't be empty")
        @field:NotBlank(message = "Field name can't be blank")
        @field:NotNull(message = "Field name can't be null")
        var name: String? = null,
        @field:NotEmpty(message = "Field description can't be empty")
        @field:NotBlank(message ="Field description can't be blank")
        @field:NotNull(message = "Field description can't be null")
        var description: String? = null,
        @field:NotNull(message = "Field price can't be null")
        var price: Double? = null,
        var producer: ProducerDto? = null
) {

    fun fromDTO() = Movie(id, name, description, price, producer = producer?.fromDTO())
}
