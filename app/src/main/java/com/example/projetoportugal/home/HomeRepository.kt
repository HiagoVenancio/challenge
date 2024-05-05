package com.example.projetoportugal.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.projetoportugal.api.ApiService
import com.example.projetoportugal.home.models.PokemonsUiModel

class HomeRepository(
    private val service: ApiService,
) {
    //suspend fun getPokemons() = service.getPokemons(20).body()
/*    suspend fun getPokemon(id: String): MutableList<PokemonsUiModel> {
        val response = service.getPokemon(id).body()

        val pokemons = mutableListOf<PokemonsUiModel>()

        val pokemonType = response?.types?.map {
            HomeViewModel.PokemonTypes.getByName(it.type.name)
        }

        pokemons.add(
            PokemonsUiModel(
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
    }*/

    fun getPokemonsPagination() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            DataPagingSource(service)
        }
    ).flow

}

