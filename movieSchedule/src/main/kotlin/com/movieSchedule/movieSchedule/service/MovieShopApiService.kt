package com.movieSchedule.movieSchedule.service

import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import com.movieSchedule.movieSchedule.model.MovieShopModel

@Service
class MovieShopApiService(val restTemplate: RestTemplate, val env: Environment) {

    fun getBaseUrl(): String? {
        return env.getProperty("movie-shop-url")
    }

    fun getMovieById(id: Long): MovieShopModel? {
        val url = "${getBaseUrl()}/movies/${id}"
        return restTemplate.getForObject(url, MovieShopModel().javaClass)
    }
}