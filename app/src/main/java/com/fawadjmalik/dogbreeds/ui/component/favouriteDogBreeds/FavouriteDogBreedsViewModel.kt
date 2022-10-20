package com.fawadjmalik.dogbreeds.ui.component.favouriteDogBreeds

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.domain.usecase.favouriteDogBreeds.FavouriteDogBreedsUseCase
import com.fawadjmalik.dogbreeds.ui.base.BaseViewModel
import com.fawadjmalik.dogbreeds.utils.extensions.capitalizeFirstLetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the FavouriteDogBreedsViewModel, all the data has been fetched and modified here. It prepares all the data for the composable.
 * And business logic has been kept abstracted from FavouriteDogBreeds composable.
 * This class has no knowledge whether data is coming from API or Database, so fulfilling complete abstraction of Data Layer.
 * We are using Flow to keep the data stream unidirectional.
 */
@HiltViewModel
class FavouriteDogBreedsViewModel @Inject constructor(
    private val dogBreedsUseCase: FavouriteDogBreedsUseCase
) : BaseViewModel() {
    var uiState by mutableStateOf(FavouriteUiState())
        private set

    init {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            dogBreedsUseCase.getFavouriteDogBreeds().collect { result ->
                uiState =
                    uiState.copy(
                        isLoading = false,
                        dogBreeds = result.map {
                            DogBreed(
                                name = it.name.capitalizeFirstLetter(),
                                subBreeds = it.subBreeds,
                                imageUrl = it.imageUrl,
                                isFavourite = it.isFavourite
                            )
                        })
            }
        }
    }
}