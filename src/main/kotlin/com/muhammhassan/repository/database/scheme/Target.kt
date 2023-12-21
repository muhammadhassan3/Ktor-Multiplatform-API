package com.muhammhassan.repository.database.scheme

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.javatime.timestamp

object Target: Table("target"){
    val id = integer("id").autoIncrement()
    val month = integer("month")
    val createdAt = datetime("created_at")

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}