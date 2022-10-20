package com.fawadjmalik.dogbreeds.domain.usecase.favouriteDogBreeds

import com.fawadjmalik.dogbreeds.data.repository.DataSource
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * As we are following the clean architecture, this is the use case class for favourite dog breeds. This
 * use case is later injected to the view model wherever it is required.
 */
class FavouriteDogBreedsUseCase @Inject constructor(
    private val dataRepository: DataSource
) : UseCase {
    override fun getFavouriteDogBreeds(): Flow<List<DogBreed>> {
        return dataRepository.getFavouriteDogBreeds()
    }

    override suspend fun addToFavourites(name: String, isFavourite: Boolean) {
        dataRepository.updateDogBreeds(name = name, isFavourite = isFavourite)
    }
}