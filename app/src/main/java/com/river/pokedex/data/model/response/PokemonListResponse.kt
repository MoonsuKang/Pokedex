package com.river.pokedex.data.model.response

import com.river.pokedex.core.model.Page
import com.river.pokedex.domain.model.PokemonListItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Pokemon 리스트 조회용 응답
@Serializable
data class PokemonListResponse(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<PokemonResultResponse>,
)

// Pokemon 리스트 응답 -> Page로 변환 확장 함수
fun PokemonListResponse.toPage(currentPage: Int): Page<PokemonListItem> {
    return Page(
        hasNext = next != null,
        page = currentPage,
        data = results.map { it.toDomain() },
    )
}
