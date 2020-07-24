package com.example.api.consumer.consumer

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(KafkaConsumer::class.java)
        private const val  TOPIC = "cliente"
    }

    @KafkaListener(topics = [TOPIC], groupId = "group_id")
    fun consumer(message: Any) {
        LOGGER.info(message.toString())
    }
}