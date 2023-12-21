package com.muhammhassan

import com.muhammhassan.plugins.DatabaseConfig
import com.muhammhassan.plugins.configureMonitoring
import com.muhammhassan.plugins.configureRouting
import com.muhammhassan.plugins.configureSerialization
import com.muhammhassan.route.registerTargetRoute
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    return EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()

    registerTargetRoute()

    val username = environment.config.propertyOrNull("ktor.application.database.username")?.getString() ?: ""
    val password = environment.config.propertyOrNull("ktor.application.database.password")?.getString() ?: ""

    DatabaseConfig.init(username, password)
}
