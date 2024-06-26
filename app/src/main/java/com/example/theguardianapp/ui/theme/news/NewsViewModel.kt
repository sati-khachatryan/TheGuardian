package com.example.theguardianapp.ui.theme.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.theguardianapp.model.Results
import com.example.theguardianapp.usecase.ArticleDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    articleDataUseCase: ArticleDataUseCase,
) : ViewModel() {

    val news: Flow<PagingData<Results>> = articleDataUseCase
        .getArticles()
        .cachedIn(viewModelScope)
}