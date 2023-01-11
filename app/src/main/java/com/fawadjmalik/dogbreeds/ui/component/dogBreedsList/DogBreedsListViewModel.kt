package com.fawadjmalik.dogbreeds.ui.component.dogBreedsList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _uiState = mutableStateOf(DogBreedsListUiState())
    val uiState: State<DogBreedsListUiState> = _uiState

    init {
        getDogBreedsList()
    }

    fun getDogBreedsList() {
        // CoroutineScope tied to this ViewModel.
        // This scope will be canceled when ViewModel will be cleared, i.e ViewModel.onCleared is called
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            dogBreedsUseCase.getDogBreeds().collect { result ->
                _uiState.value = if (result.isNotEmpty()) {
                    _uiState.value.copy(
                        isLoading = false,
                        dogBreeds = result.map { // Returns a list containing the results of applying
                            // the given transform function to each element in the original collection.
                            DogBreed(
                                name = it.name.capitalizeFirstLetter(),
                                subBreeds = it.subBreeds,
                                imageUrl = it.imageUrl,
                                isFavourite = it.isFavourite
                            )
                        })
                } else {
                    _uiState.value.copy(isLoading = false, dogBreeds = result)
                }
            }
        }
    }
}