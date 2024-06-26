package com.example.theguardianapp.repo.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.theguardianapp.model.ArticleEntity
import com.example.theguardianapp.repo.local.ArticleDatabase
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator(
    private val articleDb: ArticleDatabase,
    private val articleApi: ApiService,
): RemoteMediator<Int, ArticleEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val articles = articleApi.getData(page = loadKey, pageCount = state.config.pageSize)

            articleDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDb.articleDao().clearAll()
                }

                val articlesEntities = articles.response.results.map { it.toArticlesEntity(articles.response) }
                articleDb.articleDao().upsertAll(articlesEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = articles.response.results.isEmpty()
            )

        }catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }

    }
}