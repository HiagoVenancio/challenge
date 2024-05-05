package com.example.projetoportugal.home

import com.example.projetoportugal.api.ApiService

class HomeRepository(
    private val service: ApiService,
) {
    suspend fun getPokemons() = service.getPokemons().body()
    suspend fun getPokemon(id: String): MutableList<HomeViewModel.PokemonsUiModel> {
        val response = service.getPokemon(id).body()

        val pokemons = mutableListOf<HomeViewModel.PokemonsUiModel>()

        val pokemonType = response?.types?.map {
            HomeViewModel.PokemonTypes.getByName(it.type.name)
        }

        pokemons.add(
            HomeViewModel.PokemonsUiModel(
                id = response?.id ?: 0,
                name = id,
                image = response?.sprites?.back_default ?: "",
                elementType = pokemonType ?: emptyList(),
                moves = response?.moves ?: emptyList(),
                height = response?.height ?: 0,
                weight = response?.weight ?: 0,
                isFavorite = false
            )
        )
        return pokemons
    }
}

