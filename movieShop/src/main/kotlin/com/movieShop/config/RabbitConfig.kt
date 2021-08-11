package com.movieShop.config

import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    val topicExchangeName: String = "movie-schedule-exchange"

	val queueName: String = "movie-schedule"

    val routingKeyBase: String = "movie-shop."

}