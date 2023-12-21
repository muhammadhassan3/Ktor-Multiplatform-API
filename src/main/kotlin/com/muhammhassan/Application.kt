package com.muhammhassan

import com.muhammhassan.plugins.DatabaseConfig
import com.muhammhassan.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
//    configureSockets()
    configureSerialization()
    configureMonitoring()
//    configureHTTP()
//    configureSecurity()
    configureRouting()
    DatabaseConfig.init()
}
