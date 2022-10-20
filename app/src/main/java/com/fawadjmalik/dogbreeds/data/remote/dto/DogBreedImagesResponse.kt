package com.fawadjmalik.dogbreeds.data.remote.dto

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the data class for saving response from the API. Kept class immutable by declaring all fields as val.
 */
data class DogBreedImagesResponse(
    val status: String,
    val message: List<String>,
    val code: Int
)
