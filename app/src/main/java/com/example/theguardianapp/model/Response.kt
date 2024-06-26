package com.example.theguardianapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val status: String? = null,
    val userTier: String? = null,
    val total: Int? = null,
    var startIndex: Int,
    val pageSize: Int? = null,
    val currentPage: Int? = null,
    val pages: Int? = null,
    val orderBy: String? = null,
    val results: List<Results> = listOf()
)