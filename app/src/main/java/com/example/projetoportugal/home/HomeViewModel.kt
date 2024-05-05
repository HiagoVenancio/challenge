package com.example.projetoportugal.home

import androidx.navigation.NavHostController
import androidx.paging.PagingData
import com.example.projetoportugal.BaseViewModel
import com.example.projetoportugal.MyScreens
import com.example.projetoportugal.R
import com.example.projetoportugal.home.models.PokemonsUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val repository: HomeRepository
) : BaseViewModel() {

    private val _pokemonState = MutableStateFlow<PagingData<PokemonsUiModel>>(PagingData.empty())
    val pokemonState: StateFlow<PagingData<PokemonsUiModel>> = _pokemonState


    private val _screenState: MutableStateFlow<String> = MutableStateFlow("")
    val screenState = _screenState

    fun setCurrentScreen(screen: String) {
        screenState.update {
            screen
        }
    }

    fun getPokemons() = repository.getPokemonsPagination()

    fun navigateToPokemonDetails(pokemonName: PokemonsUiModel, navController: NavHostController) {
        navController.navigate("${MyScreens.Pokemon_Details.name}/${pokemonName.name}")
    }

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