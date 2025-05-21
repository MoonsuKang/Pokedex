package com.river.pokedex.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val height: Double,
    val weight: Double,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAttack: Int,
    val spDefense: Int,
    val speed: Int,
)
