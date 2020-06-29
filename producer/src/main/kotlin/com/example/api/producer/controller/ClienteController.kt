package com.example.api.producer.controller

import com.example.api.entitie.Cliente
import com.example.api.producer.dto.ClienteEntitieDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value=["/cliente"])
class ClienteController {

    @Autowired
    private lateinit var template : KafkaTemplate<String, Cliente>

    private val topic = "cliente"

    @GetMapping("/post")
    fun postMessage(@RequestBody cliente : ClienteEntitieDTO) {
        val cli: Cliente = Cliente.newBuilder()
                .setNome(cliente.nome)
                .setCpf(cliente.cpf)
                .setDataNasc(cliente.dataNasc)
                .build()
        template.send(topic, cli)
    }
}