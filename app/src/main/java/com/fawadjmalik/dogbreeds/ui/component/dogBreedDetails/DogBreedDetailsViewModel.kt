package com.fawadjmalik.dogbreeds.ui.component.dogBreedDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.fawadjmalik.dogbreeds.data.Resource
import com.fawadjmalik.dogbreeds.domain.usecase.dogBreedImages.DogBreedImagesUseCase
import com.fawadjmalik.dogbreeds.domain.usecase.favouriteDogBreeds.FavouriteDogBreedsUseCase
import com.fawadjmalik.dogbreeds.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the DogBreedsDetailsViewModel, all the data has been fetched and modified here. It prepares all the data for the composable.
 * And business logic has been kept abstracted from DogBreedDetails composable.
 * This class has no knowledge whether data is coming from API or Database, so fulfilling complete abstraction of Data Layer.
 */
@HiltViewModel
class DogBreedDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dogBreedImagesUseCase: DogBreedImagesUseCase,
    private val favouriteDogBreedsUseCase: FavouriteDogBreedsUseCase
) : BaseViewModel() {

    private val nameType = checkNotNull(savedStateHandle["name"]).toString()

    var uiState by mutableStateOf(DogBreedDetailsUiState())

    init {
        getDogBreedImages(nameType.lowercase())
    }

    fun getDogBreedImages(name: String) {
        viewModelScope.launch {
            dogBreedImagesUseCase.getDogBreedImages(name).collect { result ->
                handleDogBreedImagesResponse(result)
            }
        }
    }

    private fun handleDogBreedImagesResponse(result: Resource<List<String>>) {
        uiState = when (result) {
            is Resource.Success -> {
                uiState.copy(
                    isLoading = false,
                    dogBreedsImages = result.data
                )
            }
            is Resource.DataError -> {
                result.errorCode
                uiState.copy(isLoading = false, dogBreedsImages = emptyList())
            }
            is Resource.Loading -> {
                uiState.copy(isLoading = true)
            }
        }
    }

    fun addToFavourites(dogName: String, isFavourite: Boolean) {
        viewModelScope.launch {
            favouriteDogBreedsUseCase.addToFavourites(name = dogName, isFavourite = isFavourite)
        }
    }
}