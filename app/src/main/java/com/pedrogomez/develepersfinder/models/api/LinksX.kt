package com.pedrogomez.develepersfinder.models.api

import kotlinx.serialization.Serializable

@Serializable
data class LinksX(
    val html: String,
    val likes: String,
    val photos: String,
    val self: String
)