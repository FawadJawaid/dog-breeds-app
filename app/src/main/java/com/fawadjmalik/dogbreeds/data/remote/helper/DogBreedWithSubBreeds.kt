package com.fawadjmalik.dogbreeds.data.remote.helper

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the helper data class which is used to pass data inside RemoteDataSource class.
 */

/** A data class is a class that only contains state and does not perform any operation. **/
data class DogBreedWithSubBreeds(
    val name: String,
    val subBreeds: String
)
