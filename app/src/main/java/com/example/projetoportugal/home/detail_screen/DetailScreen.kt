package com.example.projetoportugal.home.detail_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.projetoportugal.R
import com.example.projetoportugal.home.HomeViewModel
import com.example.projetoportugal.home.models.PokemonsUiModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PokemonDetail(
    viewModel: HomeViewModel,
    pokemonId: String
) {
  /*  val pokemonsPage = viewModel.getPokemons().collectAsLazyPagingItems()
    val pokemon = pokemonsPage.itemSnapshotList.items.find {
        it.name == pokemonId
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        pokemon?.let {
            PokemonHeader(pokemon) {
            }
            PokemonContent(pokemon)
        }
    }*/
}

@Composable
private fun PokemonContent(pokemon: PokemonsUiModel) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Type(s): ",
                modifier = Modifier,
                color = colorResource(id = R.color.black)
            )
            pokemon.elementType.forEach {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .padding(5.dp)
                        .background(color = colorResource(id = it.color))
                )
            }
        }

        Row {
            Text(
                text = "Moves: ",
                modifier = Modifier,
                color = colorResource(id = R.color.black)
            )
            pokemon.moves.forEachIndexed { index, pokemonMoves ->
                val maxMovesToShow = 2
                val isLastToShow = index == maxMovesToShow
                if (index <= maxMovesToShow) {
                    Text(
                        text = if (isLastToShow) pokemonMoves.move.name else "${pokemonMoves.move.name}, ",
                        modifier = Modifier,
                        color = colorResource(id = R.color.black)
                    )
                }
            }
        }

        Text(
            text = "Height: ${pokemon.height}",
            modifier = Modifier,
            color = colorResource(id = R.color.black)
        )

        Text(
            text = "Weight: ${pokemon.weight}",
            modifier = Modifier,
            color = colorResource(id = R.color.black)
        )
    }
}

@Composable
private fun PokemonHeader(
    pokemon: PokemonsUiModel,
    onFavoriteClicked: (Boolean) -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }
    val icon: Painter = if (isFavorite) {
        painterResource(id = R.drawable.is_favorite)
    } else {
        painterResource(id = R.drawable.not_favorite)
    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(color = colorResource(id = R.color.third))
    )
    Row(
        modifier = Modifier
            .background(color = colorResource(id = R.color.third))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "${pokemon.name}(${pokemon.id})",
            modifier = Modifier.background(color = colorResource(id = R.color.third)),
            fontWeight = FontWeight.W500,
            color = colorResource(id = R.color.white)
        )

        Icon(
            tint = colorResource(id = R.color.primary),
            painter = icon,
            contentDescription = "Favorite Icon",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    isFavorite = !isFavorite
                    onFavoriteClicked(isFavorite)
                }
                .padding(8.dp)
                .wrapContentSize(),
        )
    }

    Box(
        Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.third))
    ) {
        AsyncImage(
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            model = pokemon.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}