package com.river.pokedex.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.river.pokedex.R
import com.river.pokedex.core.designsystem.component.ErrorText
import com.river.pokedex.core.designsystem.component.PokeDexButton
import com.river.pokedex.core.designsystem.component.PokeDexSnackBar
import com.river.pokedex.core.designsystem.component.PokeDexTopBarWithBackButton
import com.river.pokedex.core.designsystem.component.PokemonAbility
import com.river.pokedex.core.designsystem.component.PokemonDetailCard
import com.river.pokedex.core.designsystem.component.showCustomSnackBar
import com.river.pokedex.core.designsystem.theme.PokeDexTheme
import com.river.pokedex.presentation.detail.component.PokemonInformationBox
import com.river.pokedex.presentation.detail.component.PokemonTypeBox
import com.river.pokedex.presentation.utils.PokemonTypeColorProvider

@Composable
fun DetailRoute(
    pokemonName: String,
    viewModel: DetailViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.setEvent(DetailContract.DetailUiEvent.LoadDetail(pokemonName))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is DetailContract.DetailUiSideEffect.ShowSnackBar -> {
                    showCustomSnackBar(
                        scope = coroutineScope,
                        snackBarHostState = snackBarHostState,
                        message = effect.message,
                    )
                }
            }
        }
    }

    DetailScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        onClickBack = onClickBack,
        onClickAddFavorite = {
            viewModel.setEvent(
                DetailContract.DetailUiEvent.AddFavorite(state.toFavoritePokemon()),
            )
        },
        onClickDeleteFavorite = {
            viewModel.setEvent(
                DetailContract.DetailUiEvent.DeleteFavorite(id = state.id),
            )
        },
    )
}

@Composable
fun DetailScreen(
    state: DetailContract.DetailUiState,
    snackBarHostState: SnackbarHostState,
    onClickBack: () -> Unit,
    onClickAddFavorite: () -> Unit,
    onClickDeleteFavorite: (Int) -> Unit,
) {
    val typeColor = PokemonTypeColorProvider.getColor(state.types.firstOrNull().orEmpty())
    val backgroundColor = PokeDexTheme.colors.gray01

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(typeColor, backgroundColor),
        startY = 0f,
        endY = 1000f,
    )

    val backgroundModifier = if (state.isLoading) {
        Modifier.background(PokeDexTheme.colors.gray01)
    } else {
        Modifier.background(gradientBrush)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(backgroundModifier),
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding(),
            containerColor = Color.Transparent,
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState) { data ->
                    PokeDexSnackBar(
                        message = data.visuals.message,
                        label = data.visuals.actionLabel.orEmpty(),
                        onAction = { data.performAction() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                    )
                }
            },
            topBar = {
                if (!state.isLoading) {
                    PokeDexTopBarWithBackButton(
                        title = if (state.isFavorite) "Favorite Detail" else "Detail",
                        onBackClick = onClickBack,
                    )
                }
            },
            bottomBar = {
                if (!state.isLoading && state.errorMessage == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        val mainColor = if (state.isFavorite) {
                            PokeDexTheme.colors.red
                        } else {
                            PokeDexTheme.colors.mainGreen
                        }
                        PokeDexButton(
                            label = if (state.isFavorite) "DELETE FAVORITE" else "ADD FAVORITE",
                            icon = if (state.isFavorite) Icons.Default.Delete else Icons.Default.Add,
                            contentColor = mainColor,
                            pressedContentColor = mainColor.copy(alpha = 0.8f),
                            borderColor = mainColor,
                            pressedBorderColor = mainColor.copy(alpha = 0.8f),
                            onClick = {
                                if (state.isFavorite) {
                                    onClickDeleteFavorite(state.id)
                                } else {
                                    onClickAddFavorite()
                                }
                            },
                        )
                    }
                }
            },
        ) { innerPadding ->
            when {
                state.errorMessage != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center,
                    ) {
                        ErrorText(text = state.errorMessage)
                    }
                }

                else -> {
                    DetailContent(
                        state = state,
                        innerPadding = innerPadding,
                    )
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    state: DetailContract.DetailUiState,
    innerPadding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(rememberScrollState()),
    ) {
        PokemonDetailCard(
            modifier = Modifier.fillMaxWidth(),
            imageUrl = state.imageUrl,
            name = state.name,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {
            state.types.forEach { type ->
                PokemonTypeBox(typeLabel = type)
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        PokemonInformationBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            firstIconRes = R.drawable.ic_height,
            firstDescription = state.heightLabel,
            firstCategory = "Height",
            secondIconRes = R.drawable.ic_weight,
            secondDescription = state.weightLabel,
            secondCategory = "Weight",
        )

        PokemonAbility(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 24.dp),
            hp = state.hp,
            attack = state.attack,
            defense = state.defense,
            spAttack = state.spAttack,
            spDefense = state.spDefense,
            speed = state.speed,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        state = DetailContract.DetailUiState(
            isLoading = false,
            name = "리자몽",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
            types = listOf("불꽃", "비행"),
            height = 1.7,
            weight = 90.5,
            errorMessage = null,
        ),
        onClickBack = {},
        onClickAddFavorite = {},
        onClickDeleteFavorite = {},
        snackBarHostState = remember { SnackbarHostState() },
    )
}
