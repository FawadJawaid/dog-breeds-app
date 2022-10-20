package com.fawadjmalik.dogbreeds.domain.usecase.dogBreedImages

import com.fawadjmalik.dogbreeds.data.Resource
import com.fawadjmalik.dogbreeds.data.repository.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * As we are following the clean architecture, this is the use case class for dog breed images. This use case
 * is later injected to the view model wherever it is required.
 */
class DogBreedImagesUseCase @Inject constructor(
    private val dataRepository: DataSource
) : UseCase {
    override suspend fun getDogBreedImages(breed: String): Flow<Resource<List<String>>> {
        return dataRepository.getDogBreedImages(breed)
    }
}