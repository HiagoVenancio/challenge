package com.example.projetoportugal.home.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun MainScreen(
    viewModel: HomeViewModel,
    itemClick: (PokemonsUiModel) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 25.dp)) {
        ListScreen(viewModel, itemClick)
    }
}

@Composable
fun ListScreen(viewModel: HomeViewModel, itemClick: (PokemonsUiModel) -> Unit) {
    val pokemonsPage = viewModel.getPokemons().collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 6.dp, end = 6.dp, bottom = 30.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(pokemonsPage.itemCount) { item ->
                ListItem(
                    colors = colors(containerColor = colorResource(id = android.R.color.transparent)),
                    headlineContent = {
                        val itemValue = pokemonsPage[item]
                        itemValue?.let {
                            CardItem(itemValue, itemClick)
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun CardItem(itemValue: PokemonsUiModel, action: (PokemonsUiModel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                action.invoke(itemValue)
            }
    ) {
        Box(
            Modifier
                .height(150.dp)
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.third))
        ) {
            AsyncImage(
                placeholder = painterResource(id = R.mipmap.icon_pokeball_round),
                model = itemValue.image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = itemValue?.name.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.primary)),
                    fontWeight = FontWeight.W500,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}