package com.moviePayment.service

import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import com.moviePayment.model.MovieShopModel

@Service
class MovieShopApiService(@Autowired val restTemplate: RestTemplate, @Autowired val env: Environment) {

    fun getBaseUrl(): String? {
        return env.getProperty("movie-shop-url")
    }

    fun getMovieById(id: Long): MovieShopModel? {
        val url = "${getBaseUrl()}/movies/${id}"
        return restTemplate.getForObject(url, MovieShopModel().javaClass)
    }
}