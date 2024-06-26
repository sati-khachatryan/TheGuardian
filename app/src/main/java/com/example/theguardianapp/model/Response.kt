package com.example.theguardianapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerializedName("status") var status: String? = null,
    @SerializedName("userTier") var userTier: String? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("startIndex") var startIndex: Int,
    @SerializedName("pageSize") var pageSize: Int? = null,
    @SerializedName("currentPage") var currentPage: Int? = null,
    @SerializedName("pages") var pages: Int? = null,
    @SerializedName("orderBy") var orderBy: String? = null,
    @SerializedName("results") var results: List<Results> = listOf()
)