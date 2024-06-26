package com.example.theguardianapp.repo.remote

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.example.theguardianapp.model.ArticleEntity

class SearchPagingSource(
private val articleApi: ApiService,
private val query: String
) : PagingSource<Int, ArticleEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleEntity> {
        val currentPage = params.key ?: 1
        return try {
            val response = articleApi.searchData(query = query, pageCount = 20)
            val articlesEntities =
                response.response.results.map { it.toArticlesEntity(response.response) }
            val endOfPaginationReached = articlesEntities.isEmpty()
            if (response.response.results.isNotEmpty()) {
                LoadResult.Page(
                    data = articlesEntities,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleEntity>): Int? {
        return state.anchorPosition
    }
}