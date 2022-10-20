package com.fawadjmalik.dogbreeds.ui.component.favouriteDogBreeds

import com.fawadjmalik.dogbreeds.domain.model.DogBreed

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This the Favourites UI state is what needs to be displayed on the screen.
 */
data class FavouriteUiState (
    val dogBreeds: List<DogBreed>? = null,
    val isLoading: Boolean = false,
)