package com.fawadjmalik.dogbreeds.ui.component.dogBreedsList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.domain.usecase.dogBreeds.DogBreedsUseCase
import com.fawadjmalik.dogbreeds.ui.base.BaseViewModel
import com.fawadjmalik.dogbreeds.utils.extensions.capitalizeFirstLetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the DogBreedsListViewModel, all the data has been fetched and modified here. It prepares all the data for the composable.
 * And business logic has been kept abstracted from DogBreedsList composable.
 * This class has no knowledge whether data is coming from API or Database, so fulfilling complete abstraction of Data Layer.
 * We are using Flow to keep the data stream unidirectional.
 */
@HiltViewModel
class DogBreedsListViewModel @Inject constructor(
    private val dogBreedsUseCase: DogBreedsUseCase
) : BaseViewModel() {
    var uiState by mutableStateOf(DogBreedsListUiState())

    init {
        getDogBreedsList()
    }

    fun getDogBreedsList() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            dogBreedsUseCase.getDogBreeds().collect { result ->
                uiState = if (result.isNotEmpty()) {
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
                } else {
                    uiState.copy(isLoading = false, dogBreeds = result)
                }
            }
        }
    }
}