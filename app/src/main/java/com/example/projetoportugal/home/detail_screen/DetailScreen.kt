package com.example.projetoportugal.home.detail_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.projetoportugal.R
import com.example.projetoportugal.home.HomeViewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PokemonDetail(
    viewModel: HomeViewModel,
    name: String
) {

    val pokemon = viewModel.getPokemonByName(name)
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PokemonHeader(pokemon)


    }
}

@Composable
private fun PokemonHeader(pokemon: HomeViewModel.PokemonsUiModel) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(color = colorResource(id = R.color.primary))
    )
    Text(
        textAlign = TextAlign.Center,
        text = pokemon.name,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.primary)),
        fontWeight = FontWeight.W500,
        color = colorResource(id = R.color.white)
    )
    Box(
        Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.primary))
    ) {
        AsyncImage(
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            model = pokemon.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    }
}