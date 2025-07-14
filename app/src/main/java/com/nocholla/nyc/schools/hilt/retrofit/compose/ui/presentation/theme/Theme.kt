package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import java.util.Calendar

private val LightColorScheme = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF1877F2), // Facebook blue
    onPrimary = androidx.compose.ui.graphics.Color.White,
    background = androidx.compose.ui.graphics.Color.White,
    surface = androidx.compose.ui.graphics.Color(0xFFF0F2F5), // Light gray background
    onBackground = androidx.compose.ui.graphics.Color(0xFF606770), // Dark gray text
    onSurface = androidx.compose.ui.graphics.Color(0xFF606770)
)

private val DarkColorScheme = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF1877F2), // Facebook blue
    onPrimary = androidx.compose.ui.graphics.Color.White,
    background = androidx.compose.ui.graphics.Color(0xFF18191A), // Dark blue-gray
    surface = androidx.compose.ui.graphics.Color(0xFF242526),
    onBackground = androidx.compose.ui.graphics.Color(0xFFD3D4D6), // Light gray text
    onSurface = androidx.compose.ui.graphics.Color(0xFFD3D4D6)
)

@Composable
fun NYCSchoolsTheme(content: @Composable () -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val isDayTime = hour in 6..17 // 6 AM to 5:59 PM is daytime

    val colorScheme = if (isDayTime) LightColorScheme else DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}