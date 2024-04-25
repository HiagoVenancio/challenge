package com.example.projetoportugal.data

import com.example.projetoportugal.home.data.Pokemon
import com.example.projetoportugal.home.data.PokemonInfoResponse
import com.example.projetoportugal.home.data.PokemonTypes
import com.example.projetoportugal.home.data.PokemonsResponse
import com.example.projetoportugal.home.data.Sprites

internal object PokemonDataTest {

    val pokemons by lazy {
        PokemonsResponse(
            count = 1000,
            next = "next",
            results = listOf(pokemon1, pokemon2, pokemon3)
        )
    }

    val pokemon1 by lazy {
        Pokemon(
            "pikachu", "url"
        )
    }

    val pokemon2 by lazy {
        Pokemon(
            "venusaur", "url"
        )
    }

    val pokemon3 by lazy {
        Pokemon(
            "charmander", "url"
        )
    }

    val pokemonInfo by lazy {
        PokemonInfoResponse(
            sprites = Sprites(""),
            id = 10,
            types = listOf<PokemonTypes>(),
            moves = listOf(),
            height = 120,
            weight = 1000
        )
    }
}