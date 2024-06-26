package com.example.theguardianapp.repo.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.theguardianapp.model.ArticleEntity
import com.example.theguardianapp.repo.local.ArticleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteDataSourceImpl @Inject constructor(
    private val articleDb: ArticleDatabase,
    private val articleApi: ApiService
) : ArticleRemoteDataSource {

    override suspend fun getData(): Flow<PagingData<ArticleEntity>> = withContext(Dispatchers.IO) {
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = ArticleRemoteMediator(
                articleDb = articleDb,
                articleApi = articleApi,
            ),
            pagingSourceFactory = {
                articleDb.articleDao().pagingSource()
            }
        ).flow
    }

    override suspend fun searchData(query: String): Flow<PagingData<ArticleEntity>> =
        withContext(Dispatchers.IO) {
            Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    SearchPagingSource(
                        articleApi = articleApi,
                        query = query
                    )
                }
            ).flow
        }
}