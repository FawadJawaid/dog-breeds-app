package com.fawadjmalik.dogbreeds.data.local.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.domain.model.DogBreedImages
import com.fawadjmalik.dogbreeds.utils.Converters

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This class is to create Dog Breeds database.
 */
@Database(version = 1, entities = [DogBreed::class, DogBreedImages::class], exportSchema = false)
@TypeConverters(Converters::class)
abstract class DogBreedsDatabase : RoomDatabase() {

    abstract fun dogBreedsDao(): DogBreedsDao
}