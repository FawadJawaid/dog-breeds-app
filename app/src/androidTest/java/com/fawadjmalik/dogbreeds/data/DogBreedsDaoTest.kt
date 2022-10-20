package com.fawadjmalik.dogbreeds.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.fawadjmalik.dogbreeds.data.local.persistence.DogBreedsDao
import com.fawadjmalik.dogbreeds.data.local.persistence.DogBreedsDatabase
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.domain.model.DogBreedImages
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

/**
 * Author: Muhammad Fawad Jawaid Malik
 * We tried to cover all three layers data, domain and ui with our testing coverage. In this instrumented we are testing
 * our data layer, we are testing our room database DAO using JUnit and Truth.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class DogBreedsDaoTest {
    private lateinit var database: DogBreedsDatabase
    private lateinit var dogBreedsDao: DogBreedsDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DogBreedsDatabase::class.java
        ).allowMainThreadQueries().build()

        dogBreedsDao = database.dogBreedsDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }


    @Test
    fun insertAllBreeds_returnsTrue() = runBlocking {
        val breed1 = DogBreed(
            name = "african",
            subBreeds = "",
            isFavourite = false,
            imageUrl = "dummy_url"
        )
        val breed2 = DogBreed(
            name = "australian",
            subBreeds = "",
            isFavourite = false,
            imageUrl = "dummy_url"
        )
        dogBreedsDao.insertAllDogBreeds(listOf(breed1, breed2))

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            dogBreedsDao.getDogBreeds().collect {
                assertThat(it).contains(breed1)
                latch.countDown()

            }
            latch.await()
        }
        job.cancelAndJoin()
    }

    @Test
    fun updateDogBreeds_returnsTrue() = runBlocking {
        //insert breed
        val breed = DogBreed(
            name = "african",
            subBreeds = "",
            isFavourite = false,
            imageUrl = "dummy_url"
        )
        dogBreedsDao.insertDogBreed(breed)

        // create updated breed
        val updatedBreed = DogBreed(
            name = "african",
            subBreeds = "",
            isFavourite = true,
            imageUrl = "dummy_url"
        )

        // update
        dogBreedsDao.updateDogBreeds("african", true)

        // get breed and assert if it equals to updated breed
        val result = dogBreedsDao.getOneDogBreed("african")

        assertThat(result.isFavourite).isEqualTo(updatedBreed.isFavourite)
    }

    @Test
    fun insertDogBreedImages_returnsTrue() = runBlocking {
        val dogBreedImages = DogBreedImages(
            imageUrls = listOf("first_image", "second_image", "third_image", "fourth_image"),
            breedName = "african"
        )

        dogBreedsDao.insertDogBreedImages(dogBreedImages)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            dogBreedsDao.getDogBreedImages("african").let {
                assertThat(it).isEqualTo(dogBreedImages.imageUrls)
                latch.countDown()
            }
            latch.await()
        }
        job.cancelAndJoin()
    }

}