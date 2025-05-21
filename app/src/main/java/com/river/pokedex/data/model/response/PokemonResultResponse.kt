package com.river.pokedex.data.model.response

import com.river.pokedex.BuildConfig
import com.river.pokedex.domain.model.PokemonListItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Pokemon 리스트 안의 각 결과
@Serializable
data class PokemonResultResponse(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
) {
    fun toDomain(): PokemonListItem {
        val id = extractIdFromUrl(url) ?: 0
        val imageUrl = if (id != 0) {
            "${BuildConfig.POKEMON_SPRITE_BASE_URL}$id.png"
        } else {
            ""
        }

        return PokemonListItem(
            id = id,
            name = name,
            imageUrl = imageUrl,
        )
    }

    private fun extractIdFromUrl(url: String): Int? {
        if (!url.contains(PATH_POKEMON)) return null
        return url.trimEnd('/')
            .substringAfterLast(PATH_POKEMON)
            .toIntOrNull()
    }

    companion object {
        private const val PATH_POKEMON = "/pokemon/"
    }
}
