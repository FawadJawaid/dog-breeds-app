package com.fawadjmalik.dogbreeds.ui.component.dogBreedsList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fawadjmalik.dogbreeds.R
import com.fawadjmalik.dogbreeds.ui.theme.Title
import com.fawadjmalik.dogbreeds.utils.Constants.FAVOURITES
import com.fawadjmalik.dogbreeds.utils.Network.Utils.isConnected

/**
 * Author: Muhammad Fawad Jawaid Malik
 * These are the Composables for DogBreedsList. We are not using any XML code for our UI.
 */
@Composable
fun DogBreedsList(
    navController: NavHostController,
    dogBreedsListViewModel: DogBreedsListViewModel = hiltViewModel(),
    toggleTheme: () -> Unit
) {
    LazyColumn {
        item {
            TopBar(
                onToggle = {
                    toggleTheme()
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            goToFavouritesButton(navController = navController)
        }

        item {
            if (dogBreedsListViewModel.uiState.value.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(Color.White)
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        item {
            noInternetConnectionView(dogBreedsListViewModel = dogBreedsListViewModel)
        }

        dogBreedsListViewModel.uiState.value.dogBreeds?.let { list ->
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

@Composable
fun goToFavouritesButton(navController: NavHostController) {
    Button(
        onClick = { navController.navigate(FAVOURITES) },
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        shape = RectangleShape,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = stringResource(R.string.see_favourites),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.surface
        )
    }
}

@Composable
fun noInternetConnectionView(dogBreedsListViewModel: DogBreedsListViewModel) {
    val context = LocalContext.current
    if (!isConnected(context) && dogBreedsListViewModel.uiState.value.dogBreeds?.isEmpty() == true) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Title(title = stringResource(id = R.string.text_no_internet_connection))


            Button(
                onClick = { dogBreedsListViewModel.getDogBreedsList() },
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                shape = RectangleShape,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                )
            ) {
                Text(
                    text = stringResource(R.string.text_retry),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.surface
                )
            }
        }
    }
}
