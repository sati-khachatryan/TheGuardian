package com.example.theguardianapp.ui.theme.news

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.theguardianapp.ui.theme.HalfPadding
import com.example.theguardianapp.ui.theme.navigation.NavigationScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {
    val news = newsViewModel.news.collectAsLazyPagingItems()

    val context = LocalContext.current
    LaunchedEffect(key1 = news.loadState) {
        if (news.loadState.refresh is LoadState.Error) {
            val error = news.loadState.refresh as LoadState.Error
            Toast.makeText(
                context,
                "Error: ${error.error.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "articles")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavigationScreens.SearchScreen.name)
                },
                modifier = Modifier.padding(HalfPadding)
            ) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        },
        content = { padding ->
            NewsList(
                navController = navController,
                news = news,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            )
        }
    )
}