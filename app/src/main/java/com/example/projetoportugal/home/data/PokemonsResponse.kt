package com.example.projetoportugal.home.data

data class PokemonsResponse(
    val count: Int = 0,
    val next: String = "",
    val results: List<Pokemon> = emptyList(),
)

data class PokemonInfoResponse(
    val sprites: Sprites,
    val id: Int,
    val types: List<PokemonTypes>,
    val moves: List<PokemonMoves>,
    val height: Int,
    val weight: Int
)

data class PokemonMoves(
    val move: Moves
)

data class Moves(
    val name: String
)

data class PokemonTypes(
    val type: ElementType
)

data class ElementType(
    val name: String = "",
    val url: String = ""
)

data class Sprites(
    val back_default: String = ""
)


data class Pokemon(
    val name: String = "",
    val url: String = "",
)