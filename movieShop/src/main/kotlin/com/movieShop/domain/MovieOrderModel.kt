package com.movieShop.domain

import java.time.LocalDate

class MovieOrderModel(
    var orderData: LocalDate? = null,
    var movieId: Long? = null
)