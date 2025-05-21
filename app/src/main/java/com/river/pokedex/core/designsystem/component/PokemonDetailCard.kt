package com.river.pokedex.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.river.pokedex.core.designsystem.theme.PokeDexTheme

@Composable
fun PokemonDetailCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String = "Pokemon Name",
) {
    val painter = rememberAsyncImagePainter(model = imageUrl)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = name,
            color = PokeDexTheme.colors.white,
            style = PokeDexTheme.typography.head1,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonDetailCardPreview() {
    PokeDexTheme {
        PokemonDetailCard(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                .fillMaxWidth(),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png",
        )
    }
}
