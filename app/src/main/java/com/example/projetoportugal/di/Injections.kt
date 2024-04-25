package com.example.projetoportugal.di

import com.example.projetoportugal.BaseViewModel
import com.example.projetoportugal.api.RetrofitConfig
import com.example.projetoportugal.home.HomeRepository
import com.example.projetoportugal.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Injections {

    val appModule = module {
        single { RetrofitConfig.RetrofitClient.apiService }
        single<HomeRepository> { HomeRepository(get()) }

        viewModel { BaseViewModel() }
        viewModel { HomeViewModel(get()) }
    }
}