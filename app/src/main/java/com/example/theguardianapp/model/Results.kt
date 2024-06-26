package com.example.theguardianapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Results(
    val index: Int?,
    @SerializedName("webPublicationDate") var webPublicationDate: String,
    @SerializedName("webTitle") var webTitle: String,
    @SerializedName("fields") var fields: Fields
) : Parcelable