package com.river.pokedex.presentation.list

import com.river.pokedex.domain.model.PokemonListItem
import com.river.pokedex.presentation.base.UiEvent
import com.river.pokedex.presentation.base.UiSideEffect
import com.river.pokedex.presentation.base.UiState

class ListContract {
    data class ListUiState(
        val isLoading: Boolean = false,
        val pokemonList: List<PokemonListItem> = emptyList(),
        val isEndReached: Boolean = false,
        val errorMessage: String? = null,
        val favoriteIdSet: Set<Int> = emptySet(),
    ) : UiState
    sealed interface ListUiEvent : UiEvent {
        data object LoadInitial : ListUiEvent
        data object LoadNextPage : ListUiEvent
    }
    sealed interface ListUiSideEffect : UiSideEffect {
        data class ShowToast(val message: String) : ListUiSideEffect
    }
}
