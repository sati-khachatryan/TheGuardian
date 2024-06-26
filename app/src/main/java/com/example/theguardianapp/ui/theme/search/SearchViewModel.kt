package com.example.theguardianapp.ui.theme.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.theguardianapp.model.Results
import com.example.theguardianapp.usecase.ArticleDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCase: ArticleDataUseCase,
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedNews = MutableStateFlow<PagingData<Results>>(PagingData.empty())
    val searchedNews = _searchedNews

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            newsUseCase.searchData(query = query).cachedIn(viewModelScope).collect {
                _searchedNews.value = it
            }
        }
    }
}