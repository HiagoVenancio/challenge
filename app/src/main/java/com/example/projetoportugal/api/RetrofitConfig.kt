package com.example.projetoportugal.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    object RetrofitClient {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }
    }
}