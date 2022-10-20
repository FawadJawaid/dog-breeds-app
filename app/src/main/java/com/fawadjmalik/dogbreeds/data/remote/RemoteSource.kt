package com.fawadjmalik.dogbreeds.data.remote

import com.fawadjmalik.dogbreeds.data.Resource
import com.fawadjmalik.dogbreeds.domain.model.DogBreed

/**
 * Author: Muhammad Fawad Jawaid Malik
 */
interface RemoteSource {
    suspend fun getDogBreeds(): Resource<List<DogBreed>>
    suspend fun getDogBreedImages(breedName: String): Resource<List<String>>
}

