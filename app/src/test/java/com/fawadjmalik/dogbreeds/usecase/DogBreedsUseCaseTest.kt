package com.fawadjmalik.dogbreeds.usecase

import com.fawadjmalik.dogbreeds.data.repository.DataRepository
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.domain.usecase.dogBreeds.DogBreedsUseCase
import com.fawadjmalik.dogbreeds.utils.CoroutineTestRule
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the unit test class to test DogBreedsUseCase using Mockk, Junit, Kotest and Kotlinx.
 */
@ExperimentalCoroutinesApi
class DogBreedsUseCaseTest {
    lateinit var dogBreedsUseCase: DogBreedsUseCase

    private val dataRepository = mockk<DataRepository>()


    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        dogBreedsUseCase = DogBreedsUseCase(dataRepository)
    }

    @Test
    fun `fetch dog breeds`() = runTest {
        // given
        val mockDogBreeds = mockk<List<DogBreed>>(relaxed = true)

        every {
            dataRepository.getDogBreeds()
        } returns flow { emit(mockDogBreeds) }

        // when
        val useCaseValue =
            dogBreedsUseCase.getDogBreeds()

        //then
        useCaseValue.first() shouldBe mockDogBreeds
    }
}