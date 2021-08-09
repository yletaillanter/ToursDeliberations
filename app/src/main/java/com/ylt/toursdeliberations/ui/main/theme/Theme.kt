package com.ylt.toursdeliberations.ui.main.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors()
private val LightColorPalette = lightColors()

@Composable
fun MyApplicationComposeTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = ToursDelibTypography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}