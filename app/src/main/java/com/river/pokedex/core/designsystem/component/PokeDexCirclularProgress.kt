package com.river.pokedex.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.river.pokedex.core.designsystem.theme.PokeDexTheme

@Composable
fun BoxScope.PokeDexCircularProgress() {
    CircularProgressIndicator(
        modifier = Modifier.align(alignment = Alignment.Center),
        color = PokeDexTheme.colors.blue
    )
}

@Preview(showBackground = true)
@Composable
private fun PokeDexCircularProgressPreview() {
    PokeDexTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            PokeDexCircularProgress()
        }
    }
}
