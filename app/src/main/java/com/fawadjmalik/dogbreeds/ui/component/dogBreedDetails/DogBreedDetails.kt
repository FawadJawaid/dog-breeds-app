package com.fawadjmalik.dogbreeds.ui.component.dogBreedDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import com.fawadjmalik.dogbreeds.R
import com.fawadjmalik.dogbreeds.utils.Constants.COMMA
import com.fawadjmalik.dogbreeds.utils.Constants.COMMA_WITH_SPACE
import com.fawadjmalik.dogbreeds.utils.Constants.TEXT_OFFLINE
import com.fawadjmalik.dogbreeds.utils.Network
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * Author: Muhammad Fawad Jawaid Malik
 * These are the Composables for DogBreedDetails. We are not using any XML code for our UI.
 */
@Composable
fun DogBreedDetails(
    navController: NavController,
    dogBreedDetailsViewModel: DogBreedDetailsViewModel = hiltViewModel(),
    name: String,
    subBreeds: String,
    favourite: Boolean
) {
    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.text_details)) },
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
        scaffoldState = scaffoldState,
        content = {
            NoInternetConnectionView(scaffoldState)
            DetailsView(name, subBreeds, favourite, dogBreedDetailsViewModel)
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsView(
    name: String,
    subBreeds: String,
    favourite: Boolean,
    dogBreedDetailsViewModel: DogBreedDetailsViewModel
) {
    LazyColumn(
        modifier = Modifier
            .background(color = colorResource(id = R.color.background))
    ) {
        dogBreedDetailsViewModel.uiState.dogBreedsImages?.let { list ->
            item {
                val state = rememberPagerState()
                HorizontalPager(
                    count = list.size,
                    state = state
                ) { page ->
                    AsyncImage(
                        model = list[page].toUri(),
                        contentDescription = "",
                        imageLoader = LocalContext.current.imageLoader,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        alignment = Alignment.CenterStart,
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))

                DotsIndicator(
                    totalDots = list.size,
                    selectedIndex = state.currentPage,
                    selectedColor = Color.Blue,
                    unSelectedColor = Color.Gray
                )
            }
        }

        item {
            DogNameCard(name)
        }

        item {
            Title(title = stringResource(id = R.string.text_sub_breeds))
            Spacer(modifier = Modifier.height(16.dp))

            var text = stringResource(id = R.string.text_no_sub_breeds)
            if (subBreeds.isNotEmpty()) {
                text = subBreeds
            }

            Text(
                text = text.replace(COMMA, COMMA_WITH_SPACE),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 0.dp, 0.dp),
                color = colorResource(id = R.color.text),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.W300,
                textAlign = TextAlign.Start
            )
        }

        item {
            var isFavourite by remember { mutableStateOf(favourite) }

            Spacer(modifier = Modifier.height(36.dp))
            Button(
                onClick = {
                    isFavourite = if (isFavourite) {
                        dogBreedDetailsViewModel.addToFavourites(name, false)
                        false
                    } else {
                        dogBreedDetailsViewModel.addToFavourites(name, true)
                        true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if (!isFavourite) colorResource(id = R.color.blue) else colorResource(
                        id = R.color.red
                    ),
                    contentColor = Color.White
                )
            ) {
                if (!isFavourite) Text(stringResource(id = R.string.text_add_to_favourite)) else Text(
                    stringResource(id = R.string.text_remove_from_favourite)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    LazyRow(
        modifier = Modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .wrapContentHeight()

    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = colorResource(id = R.color.text),
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}

@Composable
fun NoInternetConnectionView(scaffoldState: ScaffoldState) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    if (!Network.isConnected(context)) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = TEXT_OFFLINE
            )
        }
    }
}