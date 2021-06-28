package com.movieShop.dto

import com.movieShop.domain.Producer
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ProducerDto(
        var id: Long? = null,
        @field:NotEmpty("Field name can't be empty")
        @field:NotBlank("Field name can't be blank")
        @field:NotNull("Field name can't be null")
        var name: String? = null
) {
    fun fromDTO() = Producer(id, name)
}
