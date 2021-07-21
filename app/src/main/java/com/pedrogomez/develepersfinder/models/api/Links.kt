package com.pedrogomez.develepersfinder.models.api

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val download: String,
    val html: String,
    val self: String
)