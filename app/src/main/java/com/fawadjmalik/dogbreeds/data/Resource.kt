package com.fawadjmalik.dogbreeds.data

/**
 * Author: Muhammad Fawad Jawaid Malik
 * A generic class that contains data and status about loading this data.
 */

/** A sealed class allows you to represent constraint hierachies in which objects can only be one of the given types **/
sealed class Resource<T>(
        val data: T? = null,
        val errorCode: Int? = null
) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Loading<T> : Resource<T>()
    class DataError<T>(data: T? = null, errorCode: Int) : Resource<T>(data, errorCode)
}