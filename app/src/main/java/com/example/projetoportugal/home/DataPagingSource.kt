package com.example.projetoportugal.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.projetoportugal.api.ApiService
import com.example.projetoportugal.home.models.PokemonsUiModel

class DataPagingSource(
    private val service: ApiService
) : PagingSource<Int, PokemonsUiModel>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonsUiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonsUiModel> {
        return try {
            val page = params.key ?: 1
            var offLimit = 0
            if (page > 1) {
                offLimit = ((page - 1) * 20)
            }
            val response = service.getPokemons(offLimit)

            val data = response.body()?.results?.map {
                val pokemon = service.getPokemon(it.name).body()

                val pokemonType = pokemon?.types?.map {
                    HomeViewModel.PokemonTypes.getByName(it.type.name)
                }

                PokemonsUiModel(
                    id = pokemon?.id ?: 0,
                    name = it.name,
                    image = pokemon?.sprites?.back_default ?: "",
                    elementType = pokemonType ?: emptyList(),
                    moves = pokemon?.moves ?: emptyList(),
                    height = pokemon?.height ?: 0,
                    weight = pokemon?.weight ?: 0,
                    isFavorite = false
                )
            }

            LoadResult.Page(
                data = data ?: emptyList(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()?.next.isNullOrBlank()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}