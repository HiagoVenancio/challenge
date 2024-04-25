package com.example.projetoportugal.home.data

data class PokemonsResponse(
    val count: Int = 0,
    val next: String = "",
    val results: List<Pokemon> = emptyList(),
)

data class PokemonInfoResponse(
    val sprites: Sprites
)

data class Sprites(
    val back_default: String = ""
)


data class Pokemon(
    val name: String = "",
    val url: String = "",
)