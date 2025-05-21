package com.river.pokedex.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.river.pokedex.core.designsystem.component.PokeDexCircularProgress
import com.river.pokedex.core.designsystem.component.PokeDexTopBar
import com.river.pokedex.core.designsystem.component.PokemonCard
import com.river.pokedex.core.designsystem.theme.PokeDexTheme
import com.river.pokedex.core.navigation.Route
import com.river.pokedex.core.navigation.navigateTo

@Composable
fun FavoriteRoute(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setEvent(FavoriteContract.FavoriteUiEvent.LoadFavorites)
    }

    FavoriteScreen(
        state = state,
        onClickPokemon = { pokemonName ->
            navController.navigateTo(
                Route.Detail::class,
                Route.Detail.POKEMON_NAME_KEY to pokemonName,
            )
        },
    )
}

@Composable
fun FavoriteScreen(
    state: FavoriteContract.FavoriteUiState,
    onClickPokemon: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PokeDexTheme.colors.gray02),
    ) {
        PokeDexTopBar(
            title = "Favorite",
        )

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    PokeDexCircularProgress()
                }
            }

            state.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = state.errorMessage)
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                ) {
                    items(state.favoriteList) { pokemon ->
                        PokemonCard(
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = PokeDexTheme.colors.gray01,
                            pokemonName = pokemon.name,
                            imageUrl = pokemon.imageUrl,
                            onClick = { onClickPokemon(pokemon.name) },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteScreenPreview() {
    PokeDexTheme {
        FavoriteScreen(
            state = FavoriteContract.FavoriteUiState(
                isLoading = false,
                favoriteList = emptyList(),
                errorMessage = null,
            ),
            onClickPokemon = {},
        )
    }
}
