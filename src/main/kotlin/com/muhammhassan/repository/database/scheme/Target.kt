package com.muhammhassan.repository.database.scheme

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object Target : IntIdTable("target") {
    val time = varchar("time", 7).uniqueIndex()
    val target = integer("target")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}