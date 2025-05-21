package com.river.pokedex.presentation.favorite

import com.river.pokedex.domain.usecase.ObserveFavoritePokemonUseCase
import com.river.pokedex.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val observeFavoritePokemonUseCase: ObserveFavoritePokemonUseCase,
) : BaseViewModel<FavoriteContract.FavoriteUiState, FavoriteContract.FavoriteUiEvent, FavoriteContract.FavoriteUiSideEffect>(
    FavoriteContract.FavoriteUiState(),
) {
    override suspend fun handleEvent(event: FavoriteContract.FavoriteUiEvent) {
        when (event) {
            is FavoriteContract.FavoriteUiEvent.LoadFavorites -> observeFavorites()
        }
    }

    private suspend fun observeFavorites() {
        updateState { copy(isLoading = true, errorMessage = null) }

        observeFavoritePokemonUseCase().collectLatest { favorites ->
            updateState {
                copy(
                    isLoading = false,
                    favoriteList = favorites,
                    errorMessage = null,
                )
            }
        }
    }
}
