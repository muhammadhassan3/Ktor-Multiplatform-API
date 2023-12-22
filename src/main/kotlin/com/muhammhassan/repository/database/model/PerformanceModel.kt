package com.muhammhassan.repository.database.model

import java.time.LocalDateTime

data class PerformanceModel(
    val id: Int,
    val point: Int,
    val createdAt: LocalDateTime
)
