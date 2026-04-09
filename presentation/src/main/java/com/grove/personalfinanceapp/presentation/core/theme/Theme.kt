package com.grove.personalfinanceapp.presentation.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Navy600,
    onPrimary = Slate50,
    secondary = Navy500,
    onSecondary = Slate50,
    tertiary = Blue200,
    onTertiary = Navy900,
    background = Slate50,
    onBackground = Navy900,
    surface = Slate50,
    onSurface = Navy900,
    surfaceVariant = Blue100,
    onSurfaceVariant = Navy700,
    outline = Slate200
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue200,
    onPrimary = Navy900,
    secondary = ColorSchemeTokens.secondaryDark,
    onSecondary = Navy900,
    tertiary = ColorSchemeTokens.tertiaryDark,
    onTertiary = Navy900,
    background = Navy900,
    onBackground = Slate50,
    surface = Navy800,
    onSurface = Slate50,
    surfaceVariant = Navy700,
    onSurfaceVariant = Blue100,
    outline = Navy500
)

@Composable
fun PersonalFinanceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = PersonalFinanceTypography,
        content = content
    )
}

private object ColorSchemeTokens {
    val secondaryDark = Color(0xFF78A9D4)
    val tertiaryDark = Color(0xFF8CC8FF)
}
