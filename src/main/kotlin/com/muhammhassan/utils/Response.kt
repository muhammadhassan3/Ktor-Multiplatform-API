package com.muhammhassan.utils

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val status: String,
    val data: T? = null,
    val message: String? = null
)
