package com.ameri.presentation.util.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Gunmetal,
    onPrimary = AntiFlashWhite,
    secondary = CoolGrey,
    onSecondary = AntiFlashWhite,
    background = Gunmetal,
    onBackground = AntiFlashWhite,
    surface = Gunmetal,
    onSurface = AntiFlashWhite,
    error = DarkRed,
    onError = AntiFlashWhite,

)

private val LightColorScheme = lightColorScheme(
    primary = AntiFlashWhite,
    onPrimary = Gunmetal,
    secondary = CoolGrey,
    onSecondary = Gunmetal,
    background = AntiFlashWhite,
    onBackground = Gunmetal,
    surface = AntiFlashWhite,
    onSurface = Gunmetal,
    error = MediumRed,
    onError = Gunmetal
)


@Composable
fun NewsApiTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content:
    @Composable()
        () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        useDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (view.isInEditMode.not()) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                useDarkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                useDarkTheme.not()
            window.navigationBarColor = colorScheme.background.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}