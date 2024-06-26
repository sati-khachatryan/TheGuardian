package com.example.theguardianapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Fields(
    @SerializedName("bodyText")
    val bodyText: String,
    @SerializedName("thumbnail")
    val thumbnail: String?
) : Parcelable