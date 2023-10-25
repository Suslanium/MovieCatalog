package com.suslanium.filmus.data.remote.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.suslanium.filmus.data.Constants
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN)

object LocalDateDeserializer: JsonDeserializer<LocalDate> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate {
        if (json != null) {
            return LocalDate.parse(json.asString, dateTimeFormatter)
        }
        throw IllegalArgumentException("Cannot convert null to LocalDate")
    }
}