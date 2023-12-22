package com.muhammhassan.repository.database.scheme

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object Performances : IntIdTable("performance") {
    val point = integer("point")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}