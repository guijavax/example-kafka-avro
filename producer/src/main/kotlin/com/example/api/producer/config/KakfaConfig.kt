package com.example.api.producer.config

import com.example.api.entitie.Cliente
import com.example.api.producer.serializer.AvroSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate


@Configuration
class KakfaConfig {

    @Value("\${kafka.bootstrap-servers}")
    private lateinit var servers : String

    private fun config() : Map<String, Any> {
        return HashMap<String, Any>().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer::class.java)
        }
    }

    @Bean
    fun setKafkaTemplate() : KafkaTemplate<String, Cliente> =  KafkaTemplate(DefaultKafkaProducerFactory(config()))
}