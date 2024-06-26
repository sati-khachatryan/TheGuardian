package com.example.theguardianapp.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.theguardianapp.model.Results
import com.example.theguardianapp.repo.remote.ArticleRemoteDataSource
import com.example.theguardianapp.repo.remote.toArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleDataUseCaseImpl @Inject constructor(
    private val articlesRepository: ArticleRemoteDataSource
) : ArticleDataUseCase {

    override fun getArticles(): Flow<PagingData<Results>> {
        return articlesRepository.getData()
            .map { pagingData ->
                pagingData.map { it.toArticles() }
            }
    }

    override fun searchData(query: String): Flow<PagingData<Results>> {
        return articlesRepository.searchData(query)
            .map { pagingData ->
                pagingData.map { it.toArticles() }
            }
    }
}