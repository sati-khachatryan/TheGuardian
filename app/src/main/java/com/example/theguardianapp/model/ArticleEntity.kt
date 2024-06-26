package com.example.theguardianapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: Int,
    val webTitle: String,
    val bodyText: String,
    val webPublicationDate: String,
    val imageUrl: String?
)
