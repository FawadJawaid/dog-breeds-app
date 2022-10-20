package com.fawadjmalik.dogbreeds.data.remote.helper

import androidx.room.Entity

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the helper data class which is used to pass data inside RemoteDataSource class.
 */
@Entity
data class DogBreedWithSubBreeds(
    val name: String,
    val subBreeds: String
)
