package com.movieSchedule.movieSchedule.config

import org.springframework.context.annotation.Configuration
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.context.annotation.Bean
import com.movieSchedule.movieSchedule.receiver.ReceiverMessage

@Configuration
class RabbitConfig {
    val topicExchangeName: String = "movie-schedule-exchange"

	val queueName: String = "movie-schedule"

    val routingKeyBase: String = "movie-shop.#"

    @Bean
    fun queue(): Queue {
        return Queue(queueName)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(topicExchangeName)
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyBase)
    }
    @Bean
    fun container(connectionFactory: ConnectionFactory, listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container: SimpleMessageListenerContainer = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.addQueueNames(queueName)
        container.setupMessageListener(listenerAdapter)
        return container
    }

    @Bean
    fun listenerAdapter(receiver: ReceiverMessage): MessageListenerAdapter {
        return MessageListenerAdapter(receiver,"receiveMessage")
    }
}