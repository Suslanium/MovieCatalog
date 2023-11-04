package com.suslanium.filmus.data.remote.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.IllegalArgumentException
import java.lang.reflect.Type
import java.util.UUID

object UUIDDeserializer: JsonDeserializer<UUID> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): UUID {
        if (json != null) {
            return UUID.fromString(json.asString)
        }
        throw IllegalArgumentException("Cannot convert null to UUID")
    }

}