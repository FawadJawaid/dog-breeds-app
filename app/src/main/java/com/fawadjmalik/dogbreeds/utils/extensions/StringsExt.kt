package com.fawadjmalik.dogbreeds.utils.extensions

import java.util.*

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the String extension function which we are using in multiple classes.
 * Extension Functions in Kotlin follows Decorator Pattern, it allows to extend the class or interface, without
 * making changes to the real class.
 */
fun String.capitalizeFirstLetter(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

fun String.generateSubBreedsText(): String {
    return "Has $this Sub Breeds"
}