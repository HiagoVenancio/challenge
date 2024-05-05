package com.example.projetoportugal.home.models

import com.example.projetoportugal.home.HomeViewModel
import com.example.projetoportugal.home.data.PokemonMoves
import java.io.Serializable

data class PokemonsUiModel(
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val elementType: List<HomeViewModel.PokemonTypes> = emptyList(),
    val moves: List<PokemonMoves> = emptyList(),
    val isFavorite: Boolean = false
): Serializable