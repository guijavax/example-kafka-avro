package com.example.api.producer.controller

import com.example.api.entitie.Cliente
import com.example.api.producer.dto.ClienteEntitieDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value=["/cliente"])
class ClienteController {

    @Autowired
    private lateinit var template : KafkaTemplate<String, Cliente>

    private val topic = "cliente"

    @PostMapping("/post")
    fun postMessage(@RequestBody cliente : ClienteEntitieDTO) {
        val cli = Cliente().apply {
            nome = cliente.nome
            cpf = cliente.cpf
            dataNasc = cliente.dataNasc
        }
        template.send(topic, cli)
    }
}