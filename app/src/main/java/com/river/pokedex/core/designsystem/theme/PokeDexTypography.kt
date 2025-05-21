package com.river.pokedex.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.river.pokedex.R

private val PretendardSemiBold = FontFamily(
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
)

@Immutable
data class PokeDexTypography(
    val head1: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 22.sp,
        lineHeight = 33.sp,
    ),
    val head2: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 20.sp,
        lineHeight = 30.sp,
    ),
    val head3: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 18.sp,
        lineHeight = 27.sp,
    ),
    val head4: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 17.sp,
        lineHeight = 25.5.sp,
    ),
    val body1SemiBold: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    val body2SemiBold: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 15.sp,
        lineHeight = 22.5.sp,
    ),
    val body3SemiBold: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 14.sp,
        lineHeight = 21.sp,
    ),
    val body4SemiBold: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 13.sp,
        lineHeight = 19.5.sp,
    ),
    val detail1SemiBold: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 12.sp,
        lineHeight = 18.sp,
    ),
    val detail2SemiBold: TextStyle = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 10.sp,
        lineHeight = 15.sp,
    ),
)
