package com.river.pokedex.data.datasource

import com.river.pokedex.data.model.response.PokemonDetailResponse
import com.river.pokedex.data.model.response.PokemonListResponse
import com.river.pokedex.data.service.ApiService
import com.river.pokedex.data.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListResponse {
        return safeApiCall {
            apiService.getPokemonList(offset, limit)
        }.getOrThrow()
    }
    suspend fun getPokemonDetail(name: String): PokemonDetailResponse =
        safeApiCall { apiService.getPokemonDetail(name) }.getOrThrow()
}
