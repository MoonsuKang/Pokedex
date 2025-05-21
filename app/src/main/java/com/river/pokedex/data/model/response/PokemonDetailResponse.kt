package com.river.pokedex.data.model.response

import com.river.pokedex.domain.model.PokemonDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Pokemon 상세 조회 응답
@Serializable
data class PokemonDetailResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("height") val height: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("types") val types: List<TypeSlot>,
    @SerialName("stats") val stats: List<StatSlot>,
    @SerialName("sprites") val sprites: SpritesResponse,
) {
    fun toDomain(): PokemonDetail {
        val statsMap = stats.associate { it.stat.name to it.baseStat } // key-value 묶기
        return PokemonDetail(
            id = id,
            name = name,
            imageUrl = sprites.other?.officialArtwork?.frontDefault
                ?: sprites.frontDefault.orEmpty(),
            types = types.map { it.type.name },
            height = height / 10.0,
            weight = weight / 10.0,
            hp = statsMap["hp"] ?: 0,
            attack = statsMap["attack"] ?: 0,
            defense = statsMap["defense"] ?: 0,
            spAttack = statsMap["special-attack"] ?: 0,
            spDefense = statsMap["special-defense"] ?: 0,
            speed = statsMap["speed"] ?: 0,
        )
    }
}

// Pokemon 타입 슬롯
@Serializable
data class TypeSlot(
    @SerialName("slot") val slot: Int,
    @SerialName("type") val type: TypeInfo,
)

// Pokemon 타입 정보
@Serializable
data class TypeInfo(
    @SerialName("name") val name: String,
)

@Serializable
data class StatSlot(
    @SerialName("base_stat") val baseStat: Int,
    @SerialName("stat") val stat: StatInfo,
)

@Serializable
data class StatInfo(
    @SerialName("name") val name: String,
)
