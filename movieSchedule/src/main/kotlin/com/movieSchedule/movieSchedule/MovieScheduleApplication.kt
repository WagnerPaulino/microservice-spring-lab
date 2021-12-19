package com.movieSchedule.movieSchedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.boot.runApplication
import org.springframework.web.client.RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MovieScheduleApplication {

	@Bean
	@LoadBalanced
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
		return builder.build()
	}
}

fun main(args: Array<String>) {
	runApplication<MovieScheduleApplication>(*args)
}
