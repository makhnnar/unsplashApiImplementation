package com.pedrogomez.develepersfinder.models.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandidateItem(
    @SerialName("blur_hash")
    val blurHash: String,
    val color: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("current_user_collections")
    val currentUserCollections: List<UserCollection>,
    val description: String,
    val height: Int,
    val id: String,
    @SerialName("liked_by_user")
    val likedByUser: Boolean,
    val likes: Int,
    val links: Links,
    val urls: Urls,
    val user: User,
    val width: Int
)