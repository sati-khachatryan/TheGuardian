package com.example.theguardianapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Fields(
    val bodyText: String,
    val thumbnail: String?
) : Parcelable