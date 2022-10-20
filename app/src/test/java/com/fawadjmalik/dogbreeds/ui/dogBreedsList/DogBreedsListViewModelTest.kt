package com.fawadjmalik.dogbreeds.ui.dogBreedsList

import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.domain.usecase.dogBreeds.DogBreedsUseCase
import com.fawadjmalik.dogbreeds.ui.component.dogBreedsList.DogBreedsListViewModel
import com.fawadjmalik.dogbreeds.utils.CoroutineTestRule
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the test class to test DogBreedsListViewModel using Mockk, Junit, Kotest and Kotlinx.
 */
@ExperimentalCoroutinesApi
class DogBreedsListViewModelTest {

    private lateinit var viewModel: DogBreedsListViewModel

    private val dogBreedsUseCase = mockk<DogBreedsUseCase>()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        // empty body as view model should be initialized with mocking applied
    }

    @Test
    fun `initialize then fetch dog breeds succeeded`() = runTest {
        // given
        val breed = DogBreed(
            name = "poodle",
            subBreeds = "",
            isFavourite = false,
            imageUrl = "dummy_url"
        )

        every { dogBreedsUseCase.getDogBreeds() } returns flow {
            emit(listOf(breed, breed))
        }

        // when
        // view model is initialized
        viewModel = DogBreedsListViewModel(dogBreedsUseCase)

        // then
        viewModel.uiState.dogBreeds?.size shouldBe 2
        viewModel.uiState.isLoading shouldBe false
    }

    @Test
    fun `initialize then fetch dog breeds failed`() = runTest {
        // given
        every { dogBreedsUseCase.getDogBreeds() } returns flow {
            emit(emptyList())
        }

        // when
        // view model is initialized
        viewModel = DogBreedsListViewModel(dogBreedsUseCase)

        // then
        viewModel.uiState.dogBreeds?.size shouldBe 0
        viewModel.uiState.isLoading shouldBe false
    }

    /*@Test
    fun `initialize then dog breeds is loading`() = runTest {
        // given
        every { dogBreedsUseCase.getDogBreeds() } returns flow {
            emit(emptyList())
        }

        // when
        // view model is initialized
        viewModel = DogBreedsListViewModel(dogBreedsUseCase)

        // then
        viewModel.uiState.dogBreeds shouldBe emptyList()
        viewModel.uiState.isLoading shouldBe true
    }*/
}