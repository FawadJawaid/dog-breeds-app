package com.fawadjmalik.dogbreeds.data.local.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.domain.model.DogBreedImages
import kotlinx.coroutines.flow.Flow

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the DAO (Data Access Object) class for dog breeds database. It contains all the queries for the database.
 */
@Dao
interface DogBreedsDao {

    @Query("SELECT * FROM dogbreed")
    fun getDogBreeds(): Flow<List<DogBreed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreed(dog: DogBreed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDogBreeds(dogBreeds: List<DogBreed>)

    @Query("SELECT image_urls FROM dogbreedimages WHERE breed_name =:breedName")
    suspend fun getDogBreedImages(breedName: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreedImages(dogBreeds: DogBreedImages)

    @Query("UPDATE dogbreed SET is_favourite = :isFavourite WHERE name LIKE :name")
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)

    @Query("SELECT * FROM dogbreed WHERE is_favourite = 1")
    fun getFavouriteDogBreeds(): Flow<List<DogBreed>>

    @Query("SELECT * FROM dogbreed WHERE name LIKE :name")
    fun getOneDogBreed(name: String): DogBreed
}