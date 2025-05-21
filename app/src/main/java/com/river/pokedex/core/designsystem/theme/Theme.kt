package com.river.pokedex.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalColors = staticCompositionLocalOf {
    PokedexColors()
}
private val LocalTypography = staticCompositionLocalOf {
    PokeDexTypography()
}

@Composable
fun PokeDexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(content = content)
}

object PokeDexTheme {
    val colors: PokedexColors
        @Composable
        get() = LocalColors.current
    val typography: PokeDexTypography
        @Composable
        get() = LocalTypography.current
}
