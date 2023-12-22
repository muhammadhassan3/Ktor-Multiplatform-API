package com.muhammhassan.plugins

import com.muhammhassan.route.registerPerformanceRoute
import com.muhammhassan.route.registerTargetRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    registerTargetRoute()
    registerPerformanceRoute()
}
