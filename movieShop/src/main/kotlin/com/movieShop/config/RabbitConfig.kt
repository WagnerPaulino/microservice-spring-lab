package com.movieShop.config

class RabbitConfig {

    val topicExchangeNameSchedule: String = "movie-schedule-exchange"

    val topicExchangeNamePayment: String = "movie-payment-exchange"

    val routingKeyBase: String = "movie-shop."

}