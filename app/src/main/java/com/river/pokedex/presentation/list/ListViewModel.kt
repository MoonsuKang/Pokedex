package com.river.pokedex.presentation.list

import androidx.lifecycle.viewModelScope
import com.river.pokedex.core.network.NetworkConnectivityObserver
import com.river.pokedex.core.network.NetworkStatus
import com.river.pokedex.domain.usecase.GetPokemonListUseCase
import com.river.pokedex.domain.usecase.ObserveFavoritePokemonUseCase
import com.river.pokedex.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetAddress
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val observeFavoritePokemonUseCase: ObserveFavoritePokemonUseCase,
    private val networkConnectivityObserver: NetworkConnectivityObserver,
) : BaseViewModel<ListContract.ListUiState, ListContract.ListUiEvent, ListContract.ListUiSideEffect>(
    ListContract.ListUiState(),
) {
    private var currentPage = 1
    private val pageSize = 20
    private var hasInitialized = false

    init {
        observeFavorites()
        observeNetworkStatus()
    }

    override suspend fun handleEvent(event: ListContract.ListUiEvent) {
        when (event) {
            ListContract.ListUiEvent.LoadInitial -> {
                if (!hasInitialized) {
                    loadInitial()
                }
            }

            ListContract.ListUiEvent.LoadNextPage -> {
                if (!currentState.isEndReached && !currentState.isLoading) {
                    fetchPokemonList(isInitial = false)
                }
            }
        }
    }

    private suspend fun loadInitial() {
        if (hasInitialized) return
        hasInitialized = true
        currentPage = 1
        fetchPokemonList(isInitial = true)
    }

    private suspend fun fetchPokemonList(isInitial: Boolean) {
        updateState { copy(isLoading = true, errorMessage = null) }

        try {
            val page = getPokemonListUseCase(currentPage, pageSize)
            val newList = if (isInitial) {
                page.data
            } else {
                currentState.pokemonList + page.data
            }
            updateState {
                copy(
                    isLoading = false,
                    pokemonList = newList,
                    isEndReached = !page.hasNext,
                )
            }
            currentPage++
        } catch (e: Exception) {
            updateState { copy(isLoading = false, errorMessage = e.message) }
            sendEffect(ListContract.ListUiSideEffect.ShowToast(e.message.orEmpty()))
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            observeFavoritePokemonUseCase()
                .map { favorites -> favorites.map { it.id }.toSet() }
                .collect { favoriteIdSet ->
                    updateState { copy(favoriteIdSet = favoriteIdSet) }
                }
        }
    }

    private fun observeNetworkStatus() {
        viewModelScope.launch {
            networkConnectivityObserver.networkStatus
                .distinctUntilChanged()
                .collect { status ->
                    when (status) {
                        NetworkStatus.Available -> {
                            delay(2000L)
                            if (!hasInitialized) {
                                if (isServerReachable()) {
                                    setEvent(ListContract.ListUiEvent.LoadInitial)
                                } else {
                                    sendEffect(ListContract.ListUiSideEffect.ShowToast("서버 연결에 실패했습니다."))
                                }
                            }
                        }

                        NetworkStatus.Unavailable -> {
                            sendEffect(ListContract.ListUiSideEffect.ShowToast("인터넷 연결이 끊겼습니다."))
                        }
                    }
                }
        }
    }

    private suspend fun isServerReachable(): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                InetAddress.getByName("pokeapi.co")
                true
            }
        } catch (e: Exception) {
            false
        }
    }
}
