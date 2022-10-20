package com.fawadjmalik.dogbreeds.domain.usecase.dogBreeds

import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow

/**
 * Author: Muhammad Fawad Jawaid Malik
 */
interface UseCase {
    fun getDogBreeds(): Flow<List<DogBreed>>
}