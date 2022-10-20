package com.fawadjmalik.dogbreeds.data.local

import com.fawadjmalik.dogbreeds.data.Resource
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.domain.model.DogBreedImages
import kotlinx.coroutines.flow.Flow

/**
 * Author: Muhammad Fawad Jawaid Malik
 */
interface LocalSource {
    fun getDogBreeds(): Flow<List<DogBreed>>
    suspend fun getDogBreedImages(breedName: String): Resource<List<String>>
    suspend fun storeDogBreedListInDb(dogBreeds: List<DogBreed>)
    suspend fun storeDogBreedImageListInDb(breedImages: DogBreedImages)
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)
    fun getFavouriteDogBreeds(): Flow<List<DogBreed>>
}
