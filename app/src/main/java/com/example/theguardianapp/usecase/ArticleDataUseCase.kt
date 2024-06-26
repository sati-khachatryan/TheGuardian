package com.example.theguardianapp.usecase

import androidx.paging.PagingData
import com.example.theguardianapp.model.Results
import kotlinx.coroutines.flow.Flow

interface ArticleDataUseCase {

    fun getArticles(): Flow<PagingData<Results>>

    fun searchData(query: String): Flow<PagingData<Results>>
}