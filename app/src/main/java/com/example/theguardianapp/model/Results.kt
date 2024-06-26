package com.example.theguardianapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Results(
    val index: Int?,
    @SerializedName("webPublicationDate") val webPublicationDate: String,
    @SerializedName("webTitle") val webTitle: String,
    @SerializedName("fields") val fields: Fields
) : Parcelable