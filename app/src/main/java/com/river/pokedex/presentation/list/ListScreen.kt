package com.river.pokedex.presentation.list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.river.pokedex.core.designsystem.component.PokeDexTopBar
import com.river.pokedex.core.designsystem.component.PokemonCard
import com.river.pokedex.core.designsystem.theme.PokeDexTheme
import com.river.pokedex.core.navigation.Route
import com.river.pokedex.core.navigation.navigateTo
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun ListRoute(
    viewModel: ListViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberSaveable(saver = LazyGridState.Saver) { LazyGridState() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(ListContract.ListUiEvent.LoadInitial)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ListContract.ListUiSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    ListScreen(
        state = state,
        listState = listState,
        onLoadNextPage = {
            viewModel.setEvent(ListContract.ListUiEvent.LoadNextPage)
        },
        onClickPokemon = { pokemonName ->
            navController.navigateTo(
                Route.Detail::class,
                Route.Detail.POKEMON_NAME_KEY to pokemonName,
            )
        },
    )
}

@Composable
fun ListScreen(
    state: ListContract.ListUiState,
    listState: LazyGridState,
    onLoadNextPage: () -> Unit,
    onClickPokemon: (String) -> Unit,
) {
    LaunchedEffect(listState, state.isLoading, state.pokemonList.size) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1 }
            .filter { it >= 0 }
            .distinctUntilChanged()
            .collect { lastVisibleItemIndex ->
                val shouldLoadNext = lastVisibleItemIndex >= state.pokemonList.size - 5
                if (shouldLoadNext && !state.isLoading) {
                    onLoadNextPage()
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PokeDexTheme.colors.gray02),
    ) {
        PokeDexTopBar(title = "Pokedex")

        if (state.errorMessage != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = state.errorMessage)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.pokemonList) { pokemon ->
                    PokemonCard(
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = PokeDexTheme.colors.gray01,
                        pokemonName = pokemon.name,
                        imageUrl = pokemon.imageUrl,
                        isFavorite = state.favoriteIdSet.contains(pokemon.id),
                        isLoading = state.isLoading,
                        onClick = { onClickPokemon(pokemon.name) },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview() {
    ListScreen(
        state = ListContract.ListUiState(
            isLoading = false,
            isEndReached = false,
            errorMessage = null,
        ),
        onLoadNextPage = {},
        onClickPokemon = {},
        listState = rememberLazyGridState(),
    )
}
