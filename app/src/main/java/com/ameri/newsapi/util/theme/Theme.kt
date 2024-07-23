package com.ameri.newsapi.util.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Gunmetal, // Use a dark and bold color for primary elements
    secondary = CoolGrey, // Use a slightly lighter color for secondary elements
    tertiary = AntiFlashWhite // Consider using a lighter, contrasting color for tertiary elements
)

private val LightColorScheme = lightColorScheme(
    primary = AntiFlashWhite, // Use a light and prominent color for primary elements
    secondary = CoolGrey, // Maintain consistency with the dark theme for secondary elements
    tertiary = Gunmetal // Use the darker color here for contrast and hierarchy
)


@Composable
fun NewsApiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}