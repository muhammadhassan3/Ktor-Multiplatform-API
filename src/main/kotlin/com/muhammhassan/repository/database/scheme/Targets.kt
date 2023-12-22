package com.muhammhassan.repository.database.scheme

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Targets : IntIdTable("target") {
    val time = varchar("time", 7).uniqueIndex()
    val target = integer("target")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
}

class Target(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Target>(Targets)

    var time by Targets.time
    var target by Targets.target
    val createdAt by Targets.createdAt
}