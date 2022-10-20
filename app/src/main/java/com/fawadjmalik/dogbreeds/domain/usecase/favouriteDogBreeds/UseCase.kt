package com.fawadjmalik.dogbreeds.domain.usecase.favouriteDogBreeds

import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow

/**
 * Author: Muhammad Fawad Jawaid Malik
 */
interface UseCase {
    fun getFavouriteDogBreeds(): Flow<List<DogBreed>>
    suspend fun addToFavourites(name: String, isFavourite: Boolean)
}