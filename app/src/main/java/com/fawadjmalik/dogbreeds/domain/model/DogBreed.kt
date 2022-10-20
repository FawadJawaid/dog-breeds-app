package com.fawadjmalik.dogbreeds.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the Dog Breed Entity class for DogBreeds Database, moreover, we are also using it as data class for
 * data to be presented for our domain.
 */
@Entity
data class DogBreed(
    @PrimaryKey (autoGenerate = false) val name: String,
    @ColumnInfo(name = "sub_breeds")  val subBreeds: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "is_favourite") val isFavourite: Boolean
)
