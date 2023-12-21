package com.muhammhassan.repository.database.scheme

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object Target : Table("target") {
    val id = integer("id").autoIncrement()
    val time = varchar("time", 7).uniqueIndex()
    val target = integer("target")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}