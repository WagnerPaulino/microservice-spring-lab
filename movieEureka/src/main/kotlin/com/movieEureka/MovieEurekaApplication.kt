package com.movieEureka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableEurekaServer
class MovieEurekaApplication

fun main(args: Array<String>) {
	runApplication<MovieEurekaApplication>(*args)
}
