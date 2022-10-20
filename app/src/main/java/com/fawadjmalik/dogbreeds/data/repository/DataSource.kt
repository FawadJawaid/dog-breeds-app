package com.fawadjmalik.dogbreeds.data.repository

import com.fawadjmalik.dogbreeds.data.Resource
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow


/**
 * Author: Muhammad Fawad Jawaid Malik
 * In order to have a clean architecture, I have created this interface and implementing it in DataRepository class.
*/
interface DataSource {
    fun getDogBreeds(): Flow<List<DogBreed>>
    suspend fun getDogBreedImages(breedName: String): Flow<Resource<List<String>>>
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)
    fun getFavouriteDogBreeds(): Flow<List<DogBreed>>
}
