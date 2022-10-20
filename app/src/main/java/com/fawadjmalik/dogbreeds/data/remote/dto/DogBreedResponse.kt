package com.fawadjmalik.dogbreeds.data.remote.dto

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the data class for saving response from the API. Kept class immutable by declaring all fields as val.
 */
data class DogBreedResponse(
    val status: String,
    val message: Map<String, List<String>>,
    val code: Int,
) {
    companion object {
        const val SUCCESS_STATUS = "success"
    }
}
