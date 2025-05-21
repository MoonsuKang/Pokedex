package com.river.pokedex.presentation.detail

import com.river.pokedex.domain.model.PokemonDetail
import com.river.pokedex.presentation.base.UiEvent
import com.river.pokedex.presentation.base.UiSideEffect
import com.river.pokedex.presentation.base.UiState

class DetailContract {
    data class DetailUiState(
        val isLoading: Boolean = false,
        val id: Int = 0,
        val name: String = "",
        val imageUrl: String = "",
        val types: List<String> = emptyList(),
        val height: Double = 0.0,
        val weight: Double = 0.0,
        val hp: Int = 0,
        val attack: Int = 0,
        val defense: Int = 0,
        val spAttack: Int = 0,
        val spDefense: Int = 0,
        val speed: Int = 0,
        val isFavorite: Boolean = false,
        val errorMessage: String? = null,
    ) : UiState {

        fun toFavoritePokemon(): PokemonDetail {
            return PokemonDetail(
                id = id,
                name = name,
                imageUrl = imageUrl,
                types = types,
                height = height,
                weight = weight,
                hp = hp,
                attack = attack,
                defense = defense,
                spAttack = spAttack,
                spDefense = spDefense,
                speed = speed,
            )
        }
        val heightLabel: String
            get() = "$height m"
        val weightLabel: String
            get() = "$weight kg"
    }

    sealed interface DetailUiEvent : UiEvent {
        data class LoadDetail(val name: String) : DetailUiEvent
        data class AddFavorite(val pokemon: PokemonDetail) : DetailUiEvent
        data class DeleteFavorite(val id: Int) : DetailUiEvent
    }

    sealed interface DetailUiSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : DetailUiSideEffect
    }
}
