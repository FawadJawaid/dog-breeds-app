package com.fawadjmalik.dogbreeds.data.remote.dto

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the data class for saving response from the API. Kept class immutable by declaring all fields as val.
 */

/** A data class is a class that only contains state and does not perform any operation. **/
data class DogBreedSingleImageResponse(
    val status: String,
    val message: String
)
