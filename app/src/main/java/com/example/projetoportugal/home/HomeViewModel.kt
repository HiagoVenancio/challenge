package com.example.projetoportugal.home

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.projetoportugal.BaseViewModel
import com.example.projetoportugal.MyScreens
import com.example.projetoportugal.R
import com.example.projetoportugal.home.data.PokemonMoves
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : BaseViewModel() {

    private val _pokemonState: MutableStateFlow<List<PokemonsUiModel>> = MutableStateFlow(listOf())
    val pokemonState = _pokemonState

    private val _screenState: MutableStateFlow<String> = MutableStateFlow("")
    val screenState = _screenState

    fun setCurrentScreen(screen: String) {
        screenState.update {
            screen
        }
    }

    fun getPokemons() {
        val pokemons = mutableListOf<PokemonsUiModel>()

        viewModelScope.launch(IO) {
            try {
                repository.getPokemons()?.let { data ->

                    data.results.forEach { pokemon ->
                        val response = repository.getPokemon(pokemon.name)
                        pokemons.addAll(response)
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

    fun navigateToPokemonDetails(pokemonName: String, navController: NavHostController) {
        navController.navigate("${MyScreens.Pokemon_Details.name}/$pokemonName")
    }

    data class PokemonsUiModel(
        val id: Int = 0,
        val name: String = "",
        val image: String = "",
        val height: Int = 0,
        val weight: Int = 0,
        val elementType: List<PokemonTypes> = emptyList(),
        val moves: List<PokemonMoves> = emptyList(),
        val isFavorite: Boolean = false
    )

    enum class PokemonTypes(val color: Int) {
        FIRE(color = R.color.fire),
        GRASS(color = R.color.grass),
        FLYING(color = R.color.flying),
        ELECTRIC(color = R.color.electric),
        POISON(color = R.color.poison),
        BUG(color = R.color.bug),
        NORMAL(color = R.color.normal),
        NOT_SUPPORTED(color = R.color.black);

        companion object {
            fun getByName(name: String): PokemonTypes =
                try {
                    PokemonTypes.valueOf(name.uppercase())
                } catch (e: Exception) {
                    NOT_SUPPORTED
                }
        }
    }
}