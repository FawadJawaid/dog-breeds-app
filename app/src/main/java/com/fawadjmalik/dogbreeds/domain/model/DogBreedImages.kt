package com.fawadjmalik.dogbreeds.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the Dog Breed Images Entity class for DogBreeds Database, moreover, we are also using it as data class for
 * data to be presented for our domain.
 */
@Entity
data class DogBreedImages(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "image_urls") val imageUrls: List<String>,
    @ColumnInfo(name = "breed_name") val breedName: String
)
