package com.river.pokedex.presentation.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.river.pokedex.core.designsystem.theme.PokeDexTheme
import com.river.pokedex.presentation.utils.PokemonTypeColorProvider

@Composable
fun PokemonTypeBox(
    modifier: Modifier = Modifier,
    typeLabel: String,
) {
    val backgroundColor = PokemonTypeColorProvider.getColor(typeLabel)
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = typeLabel,
            color = PokeDexTheme.colors.white,
            style = PokeDexTheme.typography.head2,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonTypeBoxPreview() {
    PokeDexTheme {
        PokemonTypeBox(
            typeLabel = "Fire",
            modifier = Modifier.padding(8.dp),
        )
    }
}
