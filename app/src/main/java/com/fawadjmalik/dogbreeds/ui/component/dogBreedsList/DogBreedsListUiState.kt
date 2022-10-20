package com.fawadjmalik.dogbreeds.ui.component.dogBreedsList

import com.fawadjmalik.dogbreeds.domain.model.DogBreed

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This the DogBreedsList UI state is what needs to be displayed on the screen.
 */
data class DogBreedsListUiState (
    var dogBreeds: List<DogBreed>? = null,
    val isLoading: Boolean = true
)