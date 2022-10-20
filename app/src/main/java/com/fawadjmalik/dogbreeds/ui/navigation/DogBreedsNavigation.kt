package com.fawadjmalik.dogbreeds.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fawadjmalik.dogbreeds.ui.component.dogBreedDetails.DogBreedDetails
import com.fawadjmalik.dogbreeds.ui.component.dogBreedsList.DogBreedsList
import com.fawadjmalik.dogbreeds.ui.component.favouriteDogBreeds.FavouriteDogBreeds
import com.fawadjmalik.dogbreeds.utils.Constants.DETAILS_ROUTE
import com.fawadjmalik.dogbreeds.utils.Constants.FAVOURITE
import com.fawadjmalik.dogbreeds.utils.Constants.MINUS_THREE_HUNDRED
import com.fawadjmalik.dogbreeds.utils.Constants.NAME
import com.fawadjmalik.dogbreeds.utils.Constants.SUB_BREEDS
import com.fawadjmalik.dogbreeds.utils.Constants.THREE_HUNDRED
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the Composables which is also responsible for the navigation inside the app.
 */
@ExperimentalAnimationApi
@Composable
fun DogBreedsNavigation(toggleTheme: () -> Unit) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, startDestination = Screen.Home.route) {
        composable(
            Screen.Home.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { MINUS_THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(THREE_HUNDRED))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { MINUS_THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(THREE_HUNDRED))
            },
        ) {
            DogBreedsList(navController, hiltViewModel(), toggleTheme)
        }
        composable(
            "${Screen.Details.route}${DETAILS_ROUTE}",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(THREE_HUNDRED))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(THREE_HUNDRED))
            },
            arguments = listOf(navArgument(NAME) { type = NavType.StringType },
                navArgument(SUB_BREEDS) { type = NavType.StringType },
                navArgument(FAVOURITE) { type = NavType.BoolType })
        ) {
            DogBreedDetails(
                navController,
                hiltViewModel(),
                it.arguments?.getString(NAME) ?: "",
                it.arguments?.getString(SUB_BREEDS) ?: "",
                it.arguments?.getBoolean(FAVOURITE) ?: false
            )
        }
        composable(
            Screen.Favourites.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { MINUS_THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(THREE_HUNDRED))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { MINUS_THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(THREE_HUNDRED))
            },
        ) {
            FavouriteDogBreeds(navController = navController, hiltViewModel())
        }
    }
}
