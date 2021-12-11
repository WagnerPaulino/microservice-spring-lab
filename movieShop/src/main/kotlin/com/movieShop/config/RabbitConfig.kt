package com.movieShop.config

import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    val topicExchangeNameSchedule: String = "movie-schedule-exchange"

    val topicExchangeNamePayment: String = "movie-payment-exchange"

	val queueName: String = "movie-schedule"

    val routingKeyBase: String = "movie-shop."

}