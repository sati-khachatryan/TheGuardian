package com.example.theguardianapp.repo.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.theguardianapp.model.ArticleEntity


@Dao
interface ArticleDao {

    @Upsert
    suspend fun upsertAll(news: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    fun pagingSource(): PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun clearAll()
}