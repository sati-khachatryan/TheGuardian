package com.example.theguardianapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    @SerializedName("response") var response: Response
)
