package com.ameri.presentation.navigation

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Splash : NavigationItem(Screen.SPLASH.name)
}