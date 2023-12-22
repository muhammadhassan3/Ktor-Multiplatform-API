package com.muhammhassan.route

import com.muhammhassan.repository.database.model.PerformanceModel
import com.muhammhassan.repository.database.scheme.Performance
import com.muhammhassan.repository.database.scheme.Performances
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

fun Application.registerPerformanceRoute(){
    routing {
        getAllData()
        addData()
        deleteData()
    }
}

private fun Route.getAllData(){
    get("/performance/"){
        newSuspendedTransaction {
            val data = Performance.all().map {
                PerformanceModel(
                    id = it.id.value,
                    createdAt = it.createdAt,
                    point = it.point
                )
            }
            call.respond(Response("success", data))
        }
    }
}

private fun Route.addData(){
    post("/performance/"){
        val body = call.receiveParameters()
        val point  = body["point"]?.toInt() ?: 0
        if(point == 0) throw ValidationException("Silahkan masukkan poin yang valid")

        newSuspendedTransaction {
            Performances.insertIgnore {
                it[this.point] = point
            }
            call.respond(HttpStatusCode.Created, Response<Nothing>("success", message = "Data berhasil ditambahkan"))
        }
    }
}

private fun Route.deleteData(){
    delete("/performance/{id}"){
        val id = call.parameters["id"]?.toInt() ?: 0
        if(id < 1) throw ValidationException("Silahkan gunakan id yang valid")
        newSuspendedTransaction {
            val result = Performances.deleteIgnoreWhere { Performances.id eq id }
            if(result > 0) call.respond(Response<Nothing>("success", message = "Data berhasil dihapus"))
            else call.respond(HttpStatusCode.BadRequest, Response<Nothing>("failed", message = "Data tidak ditemukan"))
        }
    }
}