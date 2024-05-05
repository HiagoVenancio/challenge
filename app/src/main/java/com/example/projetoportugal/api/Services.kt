package com.example.projetoportugal.api

import com.example.projetoportugal.home.data.PokemonInfoResponse
import com.example.projetoportugal.home.data.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") page: Int,
        @Query("limit") limit: Int = 20
    ): Response<PokemonsResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") pokemonId: String
    ): Response<PokemonInfoResponse>
}