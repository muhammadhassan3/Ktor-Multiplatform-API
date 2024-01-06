package com.muhammhassan.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    override fun write(out: JsonWriter?, value: LocalDateTime?) {
        out?.value(value?.format(formatter)) ?: out?.nullValue()
    }

    override fun read(`in`: JsonReader?): LocalDateTime {
        println(`in`)
        return LocalDateTime.parse(`in`?.nextString(), formatter)
    }
}