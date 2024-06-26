package com.example.theguardianapp.ui.splash

import android.view.animation.AccelerateDecelerateInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.theguardianapp.R
import com.example.theguardianapp.ui.theme.SplashIconSize
import com.example.theguardianapp.ui.theme.background
import com.example.theguardianapp.navigation.NavigationScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 100,
                easing = {
                    AccelerateDecelerateInterpolator().getInterpolation(it)
                }
            )
        )
        delay(1000)
        navController.navigate(NavigationScreens.NewsScreen.name) {
            launchSingleTop = true
            popUpTo(navController.graph.id) { inclusive = true }
        }

    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_downloading_24),
            contentDescription = "Logo",
            modifier = Modifier
                .size(SplashIconSize)
        )
    }
}