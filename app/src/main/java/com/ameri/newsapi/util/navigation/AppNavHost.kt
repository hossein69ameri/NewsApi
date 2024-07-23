package com.ameri.newsapi.util.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ameri.newsapi.ui.home.HomeScreen
import com.ameri.newsapi.ui.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    // other parameters
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.Splash.route,
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }
    }
}