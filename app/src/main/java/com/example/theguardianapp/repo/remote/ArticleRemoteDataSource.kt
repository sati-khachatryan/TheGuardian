package com.example.theguardianapp.repo.remote

import androidx.paging.PagingData
import com.example.theguardianapp.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface ArticleRemoteDataSource {

    fun getData(): Flow<PagingData<ArticleEntity>>

    fun searchData(query: String): Flow<PagingData<ArticleEntity>>
}