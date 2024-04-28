package com.example.projetoportugal.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projetoportugal.MyScreens
import com.example.projetoportugal.R
import com.example.projetoportugal.home.detail_screen.PokemonDetail
import com.example.projetoportugal.home.main_screen.MainScreen
import com.example.projetoportugal.ui.theme.ProjetoPortugalTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            actionBar?.hide()
            val navController: NavHostController = rememberNavController()
            val viewModel: HomeViewModel by viewModel()
            val errorMessage = viewModel.errorMessage.collectAsState().value
            val screenState = viewModel.screenState.collectAsState().value
            val pokemons = viewModel.pokemonState.collectAsState().value

            if (errorMessage.isNotEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }

            ProjetoPortugalTheme(
                hasStartButton = {
                    navController.popBackStack()
                },
                screenState = screenState,
                hasToolbar = true
            ) {
                NavHost(
                    modifier = Modifier.background(color = colorResource(id = R.color.secondary)),
                    navController = navController,
                    startDestination = if (pokemons.isNotEmpty()) MyScreens.Home_Main.name else MyScreens.Loading.name,
                ) {
                    viewModel.getPokemons()

                    composable(
                        route = MyScreens.Loading.name
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.width(64.dp),
                                color = MaterialTheme.colorScheme.secondary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                            if (pokemons.isNotEmpty()) navController.navigate(MyScreens.Home_Main.name)
                        }
                    }

                    composable(
                        route = MyScreens.Home_Main.name,
                    ) {
                        viewModel.setCurrentScreen(MyScreens.Home_Main.name)
                        MainScreen(
                            viewModel,
                            itemClick = {
                                navController.navigate("${MyScreens.Pokemon_Details.name}/${it}")
                            },
                        )
                    }

                    composable(
                        route = "${MyScreens.Pokemon_Details.name}/{pokemon}",
                        arguments = listOf(
                            navArgument(name = "pokemon") {
                                type = NavType.StringType
                            },
                        )
                    ) {
                        viewModel.setCurrentScreen(MyScreens.Pokemon_Details.name)
                        val name = it.arguments?.getString("pokemon")
                        PokemonDetail(
                            viewModel,
                            "$name"
                        )
                    }
                }
            }
        }
    }
}
