package com.example.theguardianapp.repo.remote

import com.example.theguardianapp.model.ArticleEntity
import com.example.theguardianapp.model.Fields
import com.example.theguardianapp.model.Response
import com.example.theguardianapp.model.Results


fun Results.toArticlesEntity(response: Response): ArticleEntity {
    return ArticleEntity(
        id = response.startIndex++,
        webTitle = webTitle,
        bodyText = fields.bodyText,
        webPublicationDate = webPublicationDate,
        imageUrl = fields.thumbnail
    )
}

fun ArticleEntity.toArticles(): Results {
    return Results(
        index = id,
        webPublicationDate = webPublicationDate,
        webTitle = webTitle,
        fields = Fields(bodyText, imageUrl),
    )
}