package com.river.pokedex.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.river.pokedex.R
import com.river.pokedex.core.designsystem.theme.PokeDexTheme

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    pokemonName: String,
    imageUrl: String,
    isFavorite: Boolean = false,
    isLoading: Boolean = false,
    surfaceHeight: Dp = 40.dp,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = backgroundColor,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (isLoading) {
                SkeletonBox(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = surfaceHeight)
                        .aspectRatio(1f),
                )
            } else {
                val painter = rememberAsyncImagePainter(
                    model = imageUrl,
                    error = painterResource(R.drawable.error),
                )

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = surfaceHeight)
                        .fillMaxSize(0.7f)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Fit,
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(surfaceHeight)
                    .align(Alignment.BottomCenter),
                color = Color.Transparent,
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = pokemonName,
                        color = PokeDexTheme.colors.white,
                        textAlign = TextAlign.Center,
                        style = PokeDexTheme.typography.body1SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            if (isFavorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "즐겨찾기",
                    tint = Color.Red,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(24.dp),
                )
            }
        }
    }
}

@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        color = PokeDexTheme.colors.red,
        style = PokeDexTheme.typography.body1SemiBold,
        modifier = modifier.padding(16.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun PokemonCardPreview() {
    PokeDexTheme {
        PokemonCard(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                .fillMaxWidth(),
            backgroundColor = PokeDexTheme.colors.white,
            pokemonName = "Pikachu",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png",
            isFavorite = true,
        )
    }
}
