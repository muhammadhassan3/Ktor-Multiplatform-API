package com.muhammhassan.route

import com.muhammhassan.repository.database.model.TargetModel
import com.muhammhassan.repository.database.scheme.Target
import com.muhammhassan.repository.database.scheme.Targets
import com.muhammhassan.utils.Response
import com.muhammhassan.utils.ValidationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteIgnoreWhere
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update

fun Application.registerTargetRoute() {
    routing {
        getAllTargets()
        saveTarget()
        editTarget()
        deleteTarget()
    }
}

private fun Route.getAllTargets() {
    get("/target/") {
        newSuspendedTransaction {
            val data = Target.all().map {
                val timeSplit = it.time.split("-")
                TargetModel(
                    id = it.id.value,
                    month = timeSplit[0].toInt(),
                    createdAt = it.createdAt,
                    year = timeSplit[1].toInt(),
                    target = it.target
                )
            }
            call.respond(Response("success", data = data))
        }
    }
}

private fun Route.saveTarget() {
    post("/target/") {
        val data = call.receiveParameters()
        val month = data["month"]?.toInt() ?: 0
        val target = data["target"]?.toInt() ?: 0
        val year = data["year"]?.toInt() ?: 0

        if (month !in 1..12) {
            throw ValidationException("Nilai bulan yang kamu gunakan tidak valid")
        }

        if (target < 2500) throw ValidationException("Target yang kamu tetapkan tidak dapat dicairkan")

        if (year !in 2020..3000) throw ValidationException("Silahkan masukkan tahun yang sesuai")

        newSuspendedTransaction {
            val result = Targets.insertIgnore {
                it[this.target] = target
                it[this.time] = "$month-$year"
            }
            if (result.insertedCount > 0) {
                call.respond(HttpStatusCode.Created, Response<Nothing>("success", message = "Data berhasil ditambahkan"))
            } else call.respond(
                HttpStatusCode.BadRequest,
                Response<Nothing>("failed", message = "Data pada tanggal tersebut sudah tersedia")
            )
        }

    }
}

private fun Route.editTarget() {
    put("/target/{id}") {
        val id = call.parameters["id"]?.toInt() ?: 0
        val target = call.receiveParameters()["target"]?.toInt() ?: 0

        if(id < 1) throw ValidationException("Silahkan masukkan nilai id yang valid")

        if (target < 2500) throw ValidationException("Target yang kamu tetapkan tidak dapat dicairkan")

        newSuspendedTransaction {
            val result = Targets.update({ Targets.id eq id }) {
                it[Targets.target] = target
            }
            if (result > 0) call.respond(Response<Nothing>("success", message = "Data berhasil diubah"))
            else call.respond(
                HttpStatusCode.BadRequest,
                Response<Nothing>("failed", message = "Data tidak ditemukan")
            )
        }
    }
}

private fun Route.deleteTarget() {
    delete("/target/{id}") {
        val id = call.parameters["id"]?.toInt() ?: 0
        if(id < 1) throw ValidationException("Silahkan masukkan nilai id yang valid")

        newSuspendedTransaction {
            val result = Targets.deleteIgnoreWhere { Targets.id eq id }
            if (result > 0) call.respond(Response<Nothing>("success", message = "Data berhasil dihapus"))
            else call.respond(
                HttpStatusCode.BadRequest,
                Response<Nothing>("failed", message = "Data tidak ditemukan")
            )
        }
    }
}