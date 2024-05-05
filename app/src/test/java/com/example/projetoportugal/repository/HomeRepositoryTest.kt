package com.example.projetoportugal.repository

import com.example.projetoportugal.api.ApiService
import com.example.projetoportugal.data.PokemonDataTest
import com.example.projetoportugal.home.HomeRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeRepositoryTest {

    private val mockService = mock<ApiService>()
    private lateinit var repository: HomeRepository

    @Before
    fun `before each test`() {
        repository = HomeRepository(mockService)
    }

    @Test
    fun `should return all pokemons`() {
     /*   runBlockingTest {
            whenever(mockService.getPokemons())
                .thenReturn(Response.success(PokemonDataTest.pokemons))

            val response = repository.getPokemons()

            Assert.assertNotNull(response)
            Assert.assertEquals(response, PokemonDataTest.pokemons)
        }*/
    }

    @Test
    fun `should return the pokemon info`() {
       /* runBlockingTest {
            whenever(mockService.getPokemon("pikachu"))
                .thenReturn(Response.success(PokemonDataTest.pokemonInfo))

            val response = repository.getPokemon("pikachu")

            Assert.assertEquals(response, PokemonDataTest.pokemonInfo)
        }*/
    }

}