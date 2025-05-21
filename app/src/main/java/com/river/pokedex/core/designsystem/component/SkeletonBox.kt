package com.river.pokedex.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.river.pokedex.core.designsystem.theme.PokeDexTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun SkeletonBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shimmer()
            .background(
                color = PokeDexTheme.colors.gray01.copy(alpha = 0.6f),
                shape = RoundedCornerShape(14.dp)
            )
    )
}

@Preview(showBackground = true)
@Composable
fun SkeletonBoxPreview() {
    PokeDexTheme {
        SkeletonBox(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        )
    }
}
