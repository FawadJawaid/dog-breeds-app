package com.fawadjmalik.dogbreeds.utils

import androidx.room.TypeConverter
import com.fawadjmalik.dogbreeds.utils.Constants.COMMA

/**
 * Author: Muhammad Fawad Jawaid Malik
 * We needed these converters to convert from String to List and vice versa. We have annotated this class as
 * TypeConverters in DogBreedsDatabase class.
 */
class Converters {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(COMMA).map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = COMMA)
    }
}