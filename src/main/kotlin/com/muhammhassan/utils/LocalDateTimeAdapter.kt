package com.muhammhassan.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {
    private val formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm")

    override fun write(out: JsonWriter?, value: LocalDateTime?) {
        out?.value(value?.format(formatter)) ?: out?.nullValue()
    }

    override fun read(`in`: JsonReader?): LocalDateTime {
        return LocalDateTime.parse(`in`?.nextString(), formatter)
    }
}