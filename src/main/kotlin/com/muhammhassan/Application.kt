package com.muhammhassan

import com.muhammhassan.plugins.DatabaseConfig
import com.muhammhassan.plugins.configureMonitoring
import com.muhammhassan.plugins.configureRouting
import com.muhammhassan.plugins.configureSerialization
import com.muhammhassan.route.registerPerformanceRoute
import com.muhammhassan.route.registerTargetRoute
import com.muhammhassan.utils.Response
import com.muhammhassan.utils.ValidationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun main(args: Array<String>) {
    return EngineMain.main(args)
}

fun Application.module() {

    install(StatusPages) {
        exception<ValidationException> { call, cause ->
            call.respond(HttpStatusCode.BadGateway, Response<Nothing>("failed", message = cause.reason))
        }

        exception<Exception> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, Response<Nothing>("failed", message = "Terjadi masalah internal pada server"))

        }
    }

    configureSerialization()
    configureMonitoring()
    configureRouting()


    val username = environment.config.propertyOrNull("ktor.application.database.username")?.getString() ?: ""
    val password = environment.config.propertyOrNull("ktor.application.database.password")?.getString() ?: ""

    DatabaseConfig.init(username, password)
}
