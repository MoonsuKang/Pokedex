package com.river.pokedex.presentation.detail

import com.river.pokedex.domain.model.PokemonDetail
import com.river.pokedex.domain.usecase.AddFavoritePokemonUseCase
import com.river.pokedex.domain.usecase.AddFavoriteResult
import com.river.pokedex.domain.usecase.DeleteFavoritePokemonUseCase
import com.river.pokedex.domain.usecase.GetPokemonDetailUseCase
import com.river.pokedex.domain.usecase.ObserveFavoritePokemonUseCase
import com.river.pokedex.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val addFavoritePokemonUseCase: AddFavoritePokemonUseCase,
    private val deleteFavoritePokemonUseCase: DeleteFavoritePokemonUseCase,
    private val observeFavoritePokemonUseCase: ObserveFavoritePokemonUseCase,
) : BaseViewModel<DetailContract.DetailUiState, DetailContract.DetailUiEvent, DetailContract.DetailUiSideEffect>(
    DetailContract.DetailUiState(),
) {

    override suspend fun handleEvent(event: DetailContract.DetailUiEvent) {
        when (event) {
            is DetailContract.DetailUiEvent.LoadDetail -> {
                fetchPokemonDetail(event.name)
            }

            is DetailContract.DetailUiEvent.AddFavorite -> {
                addFavorite(event.pokemon)
            }

            is DetailContract.DetailUiEvent.DeleteFavorite -> {
                deleteFavorite(event.id)
            }
        }
    }

    private suspend fun fetchPokemonDetail(name: String) {
        updateState { copy(isLoading = true, errorMessage = null) }

        try {
            val detail = getPokemonDetailUseCase(name)
            val favoriteList = observeFavoritePokemonUseCase().first()
            val isFavorite = favoriteList.any { it.id == detail.id }

            updateState {
                copy(
                    isLoading = false,
                    id = detail.id,
                    name = detail.name,
                    imageUrl = detail.imageUrl,
                    types = detail.types,
                    height = detail.height,
                    weight = detail.weight,
                    hp = detail.hp,
                    attack = detail.attack,
                    defense = detail.defense,
                    spAttack = detail.spAttack,
                    spDefense = detail.spDefense,
                    speed = detail.speed,
                    isFavorite = isFavorite,
                )
            }
        } catch (e: Exception) {
            updateState {
                copy(isLoading = false, errorMessage = e.message)
            }
            sendEffect(DetailContract.DetailUiSideEffect.ShowSnackBar(e.message.orEmpty()))
        }
    }

    private suspend fun addFavorite(pokemon: PokemonDetail) {
        when (val result = addFavoritePokemonUseCase(pokemon)) {
            AddFavoriteResult.Success -> {
                updateState { copy(isFavorite = true) }
                sendEffect(DetailContract.DetailUiSideEffect.ShowSnackBar("Save Success"))
            }

            AddFavoriteResult.AlreadyExists -> {
                sendEffect(DetailContract.DetailUiSideEffect.ShowSnackBar("Already exists"))
            }

            AddFavoriteResult.LimitExceeded -> {
                sendEffect(DetailContract.DetailUiSideEffect.ShowSnackBar("Limit exceeded"))
            }
        }
    }

    private suspend fun deleteFavorite(id: Int) {
        try {
            deleteFavoritePokemonUseCase(id)
            updateState { copy(isFavorite = false) }
            sendEffect(DetailContract.DetailUiSideEffect.ShowSnackBar("Delete Success"))
        } catch (e: Exception) {
            sendEffect(DetailContract.DetailUiSideEffect.ShowSnackBar(e.message.orEmpty()))
        }
    }
}
