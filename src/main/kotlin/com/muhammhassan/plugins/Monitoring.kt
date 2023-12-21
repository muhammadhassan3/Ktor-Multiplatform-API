package com.muhammhassan.plugins

import com.codahale.metrics.*
import io.ktor.server.application.*
import io.ktor.server.metrics.dropwizard.*
import java.util.concurrent.TimeUnit

fun Application.configureMonitoring() {
    install(DropwizardMetrics) {
        Slf4jReporter.forRegistry(registry)
            .withLoggingLevel(Slf4jReporter.LoggingLevel.ERROR)
            .outputTo(this@configureMonitoring.log)
            .filter(MetricFilter.startsWith("/"))
            .build()
            .start(10, TimeUnit.SECONDS)
    }
}
