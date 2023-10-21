package com.suslanium.filmus.data.remote.serialization

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.suslanium.filmus.data.Constants
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN)

object LocalDateSerializer : JsonSerializer<LocalDate> {
    override fun serialize(
        src: LocalDate?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        if (src != null) {
            return JsonPrimitive(src.atStartOfDay().format(dateTimeFormatter))
        }
        throw IllegalArgumentException("LocalDate can't be null")
    }
}