package com.example.theguardianapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerializedName("status") val status: String? = null,
    @SerializedName("userTier") val userTier: String? = null,
    @SerializedName("total") val total: Int? = null,
    @SerializedName("startIndex") var startIndex: Int,
    @SerializedName("pageSize") val pageSize: Int? = null,
    @SerializedName("currentPage") val currentPage: Int? = null,
    @SerializedName("pages") val pages: Int? = null,
    @SerializedName("orderBy") val orderBy: String? = null,
    @SerializedName("results") val results: List<Results> = listOf()
)