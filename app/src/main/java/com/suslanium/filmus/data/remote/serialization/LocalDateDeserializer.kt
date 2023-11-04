package com.suslanium.filmus.data.remote.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime

object LocalDateDeserializer: JsonDeserializer<LocalDate> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate {
        if (json != null) {
            return LocalDateTime.parse(json.asString).toLocalDate()
        }
        throw IllegalArgumentException("Cannot convert null to LocalDate")
    }
}