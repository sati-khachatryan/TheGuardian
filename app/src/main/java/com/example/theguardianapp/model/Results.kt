package com.example.theguardianapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Results(
    val index: Int?,
    val webPublicationDate: String,
    val webTitle: String,
    val fields: Fields
) : Parcelable