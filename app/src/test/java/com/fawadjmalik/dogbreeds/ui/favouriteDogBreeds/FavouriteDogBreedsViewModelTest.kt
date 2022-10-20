package com.fawadjmalik.dogbreeds.ui.favouriteDogBreeds

import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.domain.usecase.favouriteDogBreeds.FavouriteDogBreedsUseCase
import com.fawadjmalik.dogbreeds.ui.component.favouriteDogBreeds.FavouriteDogBreedsViewModel
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
 * This is the test class to test FavouriteDogBreedsViewModel using Mockk, Junit, Kotest and Kotlinx.
 */
@ExperimentalCoroutinesApi
class FavouriteDogBreedsViewModelTest {

    private lateinit var viewModel: FavouriteDogBreedsViewModel

    private val favouriteDogBreedsUseCase = mockk<FavouriteDogBreedsUseCase>()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        // empty body as view model should be initialized with mocking applied
    }

    @Test
    fun `initialize then fetch favourite dog breeds`() = runTest {
        // given
        val breed = DogBreed(
            name = "poodle",
            subBreeds = "",
            isFavourite = true,
            imageUrl = "dummy_url"
        )

        every { favouriteDogBreedsUseCase.getFavouriteDogBreeds() } returns flow {
            emit(listOf(breed, breed))
        }

        // when
        // view model is initialized
        viewModel = FavouriteDogBreedsViewModel(favouriteDogBreedsUseCase)

        // then
        viewModel.uiState.dogBreeds?.size shouldBe 2
        viewModel.uiState.isLoading shouldBe false
    }

    @Test
    fun `initialize then fetch favourite dog breeds which has no items`() = runTest {
        // given
        every { favouriteDogBreedsUseCase.getFavouriteDogBreeds() } returns flow {
            emit(emptyList())
        }

        // when
        // view model is initialized
        viewModel = FavouriteDogBreedsViewModel(favouriteDogBreedsUseCase)

        // then
        viewModel.uiState.dogBreeds?.size shouldBe 0
        viewModel.uiState.isLoading shouldBe false
    }
}