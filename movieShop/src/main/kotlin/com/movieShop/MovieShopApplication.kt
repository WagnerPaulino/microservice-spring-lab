package com.movieShop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class MovieShopApplication

fun main(args: Array<String>) {
    runApplication<MovieShopApplication>(*args)
}
