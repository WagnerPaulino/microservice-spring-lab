package com.movieShop.dto

import com.movieShop.domain.Producer
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class ProducerDto(
        var id: Long? = null,
        @field:NotEmpty(message = "Field name can't be empty")
        @field:NotBlank(message = "Field name can't be blank")
        @field:NotNull(message = "Field name can't be null")
        var name: String? = null
) {
    fun fromDTO() = Producer(id, name)
}
