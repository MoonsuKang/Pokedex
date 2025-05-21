package com.river.pokedex.presentation.favorite

import com.river.pokedex.domain.model.PokemonDetail
import com.river.pokedex.presentation.base.UiEvent
import com.river.pokedex.presentation.base.UiSideEffect
import com.river.pokedex.presentation.base.UiState

class FavoriteContract {
    data class FavoriteUiState(
        val isLoading: Boolean = false,
        val favoriteList: List<PokemonDetail> = emptyList(),
        val errorMessage: String? = null,
    ) : UiState

    sealed interface FavoriteUiEvent : UiEvent {
        data object LoadFavorites : FavoriteUiEvent
    }

    sealed interface FavoriteUiSideEffect : UiSideEffect
}
