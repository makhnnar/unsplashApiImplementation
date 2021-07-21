package com.pedrogomez.develepersfinder.models.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCollection(
    @SerialName("cover_photo")
    val coverPhoto: String,
    val id: Int,
    @SerialName("last_collected_at")
    val lastCollectedAt: String,
    @SerialName("published_at")
    val publishedAt: String,
    val title: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val user: String
)