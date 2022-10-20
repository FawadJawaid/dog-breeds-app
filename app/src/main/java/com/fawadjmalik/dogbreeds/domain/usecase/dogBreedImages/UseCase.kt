package com.fawadjmalik.dogbreeds.domain.usecase.dogBreedImages

import com.fawadjmalik.dogbreeds.data.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Author: Muhammad Fawad Jawaid Malik
 */
interface UseCase {
    suspend fun getDogBreedImages(breed : String): Flow<Resource<List<String>>>
}