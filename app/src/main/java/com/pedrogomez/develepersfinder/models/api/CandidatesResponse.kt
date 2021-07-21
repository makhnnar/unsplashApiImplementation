package com.pedrogomez.develepersfinder.models.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandidatesResponse(
    val results: List<CandidateItem>,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int
)