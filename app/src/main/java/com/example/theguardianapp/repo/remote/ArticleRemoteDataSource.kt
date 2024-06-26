package com.example.theguardianapp.repo.remote

import androidx.paging.PagingData
import com.example.theguardianapp.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface ArticleRemoteDataSource {

    suspend fun getData(): Flow<PagingData<ArticleEntity>>

    suspend fun searchData(query: String): Flow<PagingData<ArticleEntity>>
}