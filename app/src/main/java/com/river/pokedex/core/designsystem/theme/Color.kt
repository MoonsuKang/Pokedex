package com.river.pokedex.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Black = Color(0xFF111111)
val Red = Color(0xFFFF5247)
val LightGreen = Color(0xFFA9F1B0)
val MainGreen = Color(0xFF47D27D)
val DarkGreen = Color(0xFF1C9D5F)

// Yellow Scale
val LightYellow = Color(0xFFFFF5B1)
val MainYellow = Color(0xFFFFE63C)
val DarkYellow = Color(0xFFFFDE00)

// Blue Scale
val LightBlue = Color(0xFFE8F3FF)
val Blue = Color(0xFF5277FC)

// Gray Scale
val Gray01 = Color(0xFF282A31)
val Gray02 = Color(0xFF3C3E48)
val Gray03 = Color(0xFF4A4C54)
val Gray04 = Color(0xFF9396A2)
val Gray05 = Color(0xFFA7A9B2)
val Gray06 = Color(0xFFC3C6D0)
val Gray07 = Color(0xFFE3E6ED)
val Gray08 = Color(0xFFF2F3F6)
val Gray09 = Color(0xFFF8F9FC)
val White = Color(0xFFFFFFFF)

@Immutable
data class PokedexColors(
    val red: Color = Red,
    val lightGreen: Color = LightGreen,
    val mainGreen: Color = MainGreen,
    val darkGreen: Color = DarkGreen,
    val lightYellow: Color = LightYellow,
    val mainYellow: Color = MainYellow,
    val darkYellow: Color = DarkYellow,
    val lightBlue: Color = LightBlue,
    val blue: Color = Blue,
    val gray01: Color = Gray01,
    val gray02: Color = Gray02,
    val gray03: Color = Gray03,
    val gray04: Color = Gray04,
    val gray05: Color = Gray05,
    val gray06: Color = Gray06,
    val gray07: Color = Gray07,
    val gray08: Color = Gray08,
    val gray09: Color = Gray09,
    val white: Color = White,
    val black: Color = Black,
)
