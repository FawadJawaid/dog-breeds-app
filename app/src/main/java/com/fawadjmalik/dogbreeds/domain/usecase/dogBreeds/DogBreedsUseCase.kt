package com.fawadjmalik.dogbreeds.domain.usecase.dogBreeds

import com.fawadjmalik.dogbreeds.data.repository.DataSource
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * As we are following the clean architecture, this is the use case class for dog breeds. This use case
 * is later injected to the view model wherever it is required.
 */
class DogBreedsUseCase @Inject constructor(
    private val dataRepository: DataSource
) : UseCase {
     override fun getDogBreeds(): Flow<List<DogBreed>> {
        return dataRepository.getDogBreeds()
    }
}