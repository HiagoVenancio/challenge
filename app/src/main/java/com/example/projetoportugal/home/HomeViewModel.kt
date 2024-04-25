package com.example.projetoportugal.home

import androidx.lifecycle.viewModelScope
import com.example.projetoportugal.BaseViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : BaseViewModel() {

    private val _pokemonState: MutableStateFlow<List<PokemonsUiModel>> = MutableStateFlow(listOf())
    val pokemonState = _pokemonState

    fun getPokemons() {
        val pokemons = mutableListOf<PokemonsUiModel>()

        viewModelScope.launch(IO) {
            try {
                repository.getPokemons()?.let { data ->

                    data.results.forEach { pokemon ->
                        val response = repository.getPokemon(pokemon.name)
                        pokemons.add(
                            PokemonsUiModel(
                                name = pokemon.name,
                                image = response?.sprites?.back_default ?: ""
                            )
                        )
                    }

                    _pokemonState.update {
                        pokemons
                    }
                }
            } catch (e: Exception) {
                setErrorMessage("Error retrieving pokemons")
            }
        }
    }

    fun getPokemonByName(name: String): PokemonsUiModel {
        val pokemon = _pokemonState.value.find {
            it.name == name
        }
        return pokemon ?: PokemonsUiModel()
    }

    data class PokemonsUiModel(
        val name: String = "",
        val image: String = "",
        val power: Int = 0

    )
}