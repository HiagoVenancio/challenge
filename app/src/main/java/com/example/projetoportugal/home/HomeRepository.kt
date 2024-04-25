package com.example.projetoportugal.home

import com.example.projetoportugal.api.ApiService

class HomeRepository(
    private val service: ApiService
) {
    suspend fun getPokemons() = service.getPokemons().body()
    suspend fun getPokemon(id: String) = service.getPokemon(id).body()
}