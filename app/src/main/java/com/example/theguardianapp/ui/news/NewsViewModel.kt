package com.example.theguardianapp.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.theguardianapp.model.Results
import com.example.theguardianapp.usecase.ArticleDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val articleDataUseCase: ArticleDataUseCase,
) : ViewModel() {

    init {
        loadNews()
    }

    private val _loaded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loaded: StateFlow<Boolean> = _loaded

    private var _news: Flow<PagingData<Results>> = emptyFlow()

    fun getNews(): Flow<PagingData<Results>> {
        return _news
    }

    private fun loadNews() = viewModelScope.launch {
        _news = articleDataUseCase
            .getArticles()
            .cachedIn(viewModelScope)

        _loaded.value = true
    }
}