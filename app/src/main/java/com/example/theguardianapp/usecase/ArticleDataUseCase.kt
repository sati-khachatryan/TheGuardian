package com.example.theguardianapp.usecase

import androidx.paging.PagingData
import com.example.theguardianapp.model.Results
import kotlinx.coroutines.flow.Flow

interface ArticleDataUseCase {

    suspend fun getArticles(): Flow<PagingData<Results>>

    suspend fun searchData(query: String): Flow<PagingData<Results>>
}