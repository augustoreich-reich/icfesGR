package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = CgrGreenPrimaryDark,
    onPrimary = Color(0xFF062E16),
    primaryContainer = CgrGreenContainerDark,
    onPrimaryContainer = Color(0xFFD1FAE5),
    secondary = CgrRedSecondaryDark,
    onSecondary = Color(0xFF450A0A),
    secondaryContainer = Color(0xFF7F1D1D),
    onSecondaryContainer = Color(0xFFFEE2E2),
    tertiary = CgrYellowAccentDark,
    onTertiary = Color(0xFF422006),
    tertiaryContainer = Color(0xFF713F12),
    onTertiaryContainer = Color(0xFFFEF9C3),
    background = SurfaceBackgroundDark,
    onBackground = Color(0xFFECFDF5),
    surface = CardSurfaceDark,
    onSurface = Color(0xFFECFDF5),
    surfaceVariant = Color(0xFF1E3A2B),
    onSurfaceVariant = Color(0xFFA7F3D0)
)

private val LightColorScheme = lightColorScheme(
    primary = CgrGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = CgrGreenContainer,
    onPrimaryContainer = CgrGreenDark,
    secondary = CgrRedSecondary,
    onSecondary = Color.White,
    secondaryContainer = CgrRedContainer,
    onSecondaryContainer = Color(0xFF7F1D1D),
    tertiary = CgrYellowDark,
    onTertiary = Color.White,
    tertiaryContainer = CgrYellowContainer,
    onTertiaryContainer = Color(0xFF713F12),
    background = SurfaceBackgroundLight,
    onBackground = TextPrimaryLight,
    surface = CardSurfaceLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = Color(0xFFE6EFEA),
    onSurfaceVariant = TextSecondaryLight
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Mantenemos dynamicColor = false por defecto para preservar fielmente los colores del escudo del Colegio
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
