package com.fawadjmalik.dogbreeds.ui.component.favouriteDogBreeds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fawadjmalik.dogbreeds.R
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.ui.component.dogBreedsList.ItemDogCard
import com.fawadjmalik.dogbreeds.ui.theme.Title

/**
 * Author: Muhammad Fawad Jawaid Malik
 * These are the Composables for Favourite Dog Breeds screen. We are not using any XML code for our UI.
 */
@Composable
fun FavouriteDogBreeds(
    navController: NavController,
    favouritesViewModel: FavouriteDogBreedsViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.text_favourites)) },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = colorResource(id = R.color.text),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = colorResource(id = R.color.text)
                    )
                }
            )
        },

        content = {
            favouritesViewModel.uiState.dogBreeds?.let {
                FavouriteDogBreedsList(navController, it)
            }
        }
    )
}

@Composable
fun FavouriteDogBreedsList(navController: NavController, list: List<DogBreed>) {
    if (list.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title(title = stringResource(id = R.string.text_no_favourites))
        }
    } else {
        LazyColumn {
            items(list) {
                ItemDogCard(
                    it,
                    onItemClicked = { dog ->
                        navController.navigate("details/${dog.name}/${dog.isFavourite}?subBreeds=${dog.subBreeds}")
                    }
                )
            }
        }
    }
}
