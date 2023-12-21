package com.muhammhassan.repository.database.model

import java.time.LocalDateTime

data class TargetModel(val id: Int, val month: Int, val year: Int, val target: Int, val createdAt: LocalDateTime)
