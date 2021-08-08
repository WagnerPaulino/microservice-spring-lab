package com.movieSchedule.movieSchedule.receiver

import org.springframework.stereotype.Component

@Component
public class ReceiverMessage {

    fun receiveMessage(message: String) {
        print(message)
    }
}