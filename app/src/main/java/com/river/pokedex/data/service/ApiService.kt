package com.river.pokedex.data.service

import com.river.pokedex.data.model.response.PokemonDetailResponse
import com.river.pokedex.data.model.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int, // 몇 번째 항목부터 가져올껀지
        @Query("limit") limit: Int, // 한 페이지에 가져올 항목 수
    ): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String,
    ): PokemonDetailResponse
}
