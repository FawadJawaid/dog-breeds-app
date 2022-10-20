package com.fawadjmalik.dogbreeds.utils

/**
 * Author: Muhammad Fawad Jawaid Malik
 * In order to avoid Magic values or hardcoded strings in our composables and classes, we have created this Constants object class.
 */
object Constants {
    const val NAME = "name"
    const val SUB_BREEDS = "subBreeds"
    const val FAVOURITE = "favourite"
    const val HOME = "home"
    const val DETAILS = "details"
    const val FAVOURITES = "favourites"
    const val THREE_HUNDRED = 300
    const val MINUS_THREE_HUNDRED = -300
    const val DETAILS_ROUTE = "/{name}/{favourite}?subBreeds={subBreeds}"
    const val ZERO = "0"
    const val ONE = "1"
    const val COMMA = ","
    const val COMMA_WITH_SPACE = ", "
    const val DEFAULT_IS_FAVOURITE = false
    const val TEXT_OFFLINE = "You are using app offline, images might not load but you can still add items to favourites."
    const val DB_NAME = "dog_breeds"
    const val BASE_URL = "https://dog.ceo/api/"
    const val DARK_THEME = "Dark Theme"
}