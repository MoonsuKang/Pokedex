package com.river.pokedex.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// 포켓몬 스프라이트(이미지) 정보
@Serializable
data class SpritesResponse(
    @SerialName("front_default") val frontDefault: String? = null,
    @SerialName("other") val other: OtherSpritesResponse? = null,
)

@Serializable
data class OtherSpritesResponse(
    @SerialName("official-artwork") val officialArtwork: OfficialArtworkResponse? = null,
)

@Serializable
data class OfficialArtworkResponse(
    @SerialName("front_default") val frontDefault: String? = null,
)
