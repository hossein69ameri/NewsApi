package com.ameri.presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ameri.presentation.R
import com.ameri.presentation.navigation.Screen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.news))
        LottieAnimation(
            composition,
            iterations = 1
        )
    }
    LaunchedEffect(true) {
        delay(3000)
        navController.navigate(Screen.HOME.name){
            popUpTo(Screen.SPLASH.name){
                inclusive = true
            }
        }
    }
}