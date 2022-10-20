package com.fawadjmalik.dogbreeds.ui.navigation

import androidx.annotation.StringRes
import com.fawadjmalik.dogbreeds.R
import com.fawadjmalik.dogbreeds.utils.Constants.DETAILS
import com.fawadjmalik.dogbreeds.utils.Constants.FAVOURITES
import com.fawadjmalik.dogbreeds.utils.Constants.HOME

/**
 * Author: Muhammad Fawad Jawaid Malik
 * Sealed class for different navigation routes.
 */
sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen(HOME, R.string.text_home)
    object Details : Screen(DETAILS, R.string.text_details)
    object Favourites : Screen(FAVOURITES, R.string.text_favourites)
}
