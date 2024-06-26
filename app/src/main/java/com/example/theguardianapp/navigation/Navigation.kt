package com.example.theguardianapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.theguardianapp.model.Results
import com.example.theguardianapp.ui.splash.SplashScreen
import com.example.theguardianapp.ui.news.NewsScreen
import com.example.theguardianapp.ui.articles.ArticleScreen
import com.example.theguardianapp.ui.search.SearchScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.SplashScreen.name
    ) {
        composable(route = NavigationScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = NavigationScreens.NewsScreen.name) {
            NewsScreen(navController = navController)
        }

        composable(route = NavigationScreens.ArticleScreen.name) {
            navController.previousBackStackEntry?.arguments?.getParcelable(NAV_NEWS_ITEM, Results::class.java)?.let { item ->
                ArticleScreen(item = item)
            }
        }

        composable(route = NavigationScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
    }
}

sealed class NavigationScreens(val name: String) {
    data object SplashScreen : NavigationScreens("splashScreen")
    data object NewsScreen : NavigationScreens("newsScreen")
    data object ArticleScreen : NavigationScreens("articleScreen")
    data object SearchScreen : NavigationScreens("searchScreen")
}

const val NAV_NEWS_ITEM = "article"