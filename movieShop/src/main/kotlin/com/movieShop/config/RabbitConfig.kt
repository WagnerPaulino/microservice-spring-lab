package com.movieShop.config

import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    val topicExchangeNameSchedule: String = "movie-schedule-exchange"

    val topicExchangeNamePayment: String = "movie-payment-exchange"

    val routingKeyBase: String = "movie-shop."

}