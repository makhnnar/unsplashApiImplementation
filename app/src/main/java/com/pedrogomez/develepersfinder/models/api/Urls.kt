package com.pedrogomez.develepersfinder.models.api

import kotlinx.serialization.Serializable

@Serializable
data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)