package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Facebook-like colors
val FacebookBlue = Color(0xFF1877F2) // A common Facebook blue
val FacebookDarkBlue = Color(0xFF0C5DCC) // Darker shade for pressed state or dark theme primary
val FacebookLightGray = Color(0xFFF0F2F5) // Background for light theme
val FacebookGray = Color(0xFF8A8D91) // Text/icon color
val FacebookDarkGray = Color(0xFF242526) // Background for dark theme

private val DarkColorScheme = darkColorScheme(
    primary = FacebookBlue, // Main color for components
    onPrimary = Color.White, // Color for content on primary
    primaryContainer = FacebookDarkBlue, // A darker shade for containers
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF28A745), // A secondary color, e.g., for success or highlights (like a green for comments)
    onSecondary = Color.White,
    background = FacebookDarkGray, // Dark background
    onBackground = Color.White, // Text on dark background
    surface = Color(0xFF3A3B3C), // Card/surface color in dark mode
    onSurface = Color.White, // Text on surface in dark mode
    error = Color(0xFFCF6679),
    onError = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = FacebookBlue, // Main color for components
    onPrimary = Color.White, // Color for content on primary
    primaryContainer = FacebookDarkBlue, // A darker shade for containers
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF28A745), // A secondary color
    onSecondary = Color.White,
    background = FacebookLightGray, // Light background
    onBackground = Color.Black, // Text on light background
    surface = Color.White, // Card/surface color in light mode
    onSurface = Color.Black, // Text on surface in light mode
    error = Color(0xFFB00020),
    onError = Color.White
)

@Composable
fun NYCSchoolsTheme(
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
        typography = Typography, // Ensure Typography is defined elsewhere if not already
        content = content
    )
}