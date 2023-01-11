package com.fawadjmalik.dogbreeds.utils.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the asyncAll coroutines extension function which we are using in RemoteDataSource class.
 * Extension Functions in Kotlin follows Decorator Pattern, it allows to extend the class or interface, without
 * making changes to the real class.
 */
fun <T, V> CoroutineScope.asyncAll(list: List<T>, block: suspend (T) -> V): List<Deferred<V>> {
    return list.map {
        async { block.invoke(it) } // Creates a coroutine and returns its future result as an implementation of Deferred.
    }
}