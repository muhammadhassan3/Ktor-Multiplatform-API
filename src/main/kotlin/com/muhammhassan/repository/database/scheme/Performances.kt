package com.muhammhassan.repository.database.scheme

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Performances : IntIdTable("performance") {
    val point = integer("point")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
}

class Performance(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Performance>(Performances)

    var point by Performances.point
    val createdAt by Performances.createdAt
}