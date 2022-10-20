package com.fawadjmalik.dogbreeds.ui.component.dogBreedDetails

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This the DogBreedDetails UI state is what needs to be displayed on the screen.
 */
data class DogBreedDetailsUiState (
    val dogBreedsImages: List<String>? = null,
    val isLoading: Boolean = false,
)