package com.example.api.producer.serializer

import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DatumWriter
import org.apache.avro.io.EncoderFactory
import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import java.io.ByteArrayOutputStream
import java.io.IOException

class AvroSerializer<T : SpecificRecordBase> : Serializer<T> {

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {

    }

    override fun serialize(topic: String?, data: T): ByteArray? {
        return try {
            var res : ByteArray? = null
            if (data != null) {
                val byteOut = ByteArrayOutputStream()
                val enconder = EncoderFactory.get().binaryEncoder(byteOut, null)
                GenericDatumWriter<GenericRecord>(data.schema).apply {
                    write(data, enconder)
                }
                enconder.flush()
                byteOut.close()
            }
            res
        } catch (e: IOException) {
            throw SerializationException("A mensagem: $data nao pode ser serializada para o topico:  $topic ")
        }
    }
}
