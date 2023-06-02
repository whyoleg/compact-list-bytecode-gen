@file:JvmMultifileClass
@file:JvmName("CompactListKt")

package dev.whyoleg.testtask

import kotlin.reflect.*

/**
 * List implementation with possibility to [add] and [get] elements.
 *
 * @param T type of elements in the list
 */
public interface CompactList<T> {
    /**
     * Returns number of elements in the list
     */
    public val size: Int

    /**
     * Adds a specified element to the end of the list
     *
     * @throws OutOfMemoryError if it's not possible to add a new element because of VM restrictions
     */
    public fun add(value: T)

    /**
     * Returns an element at specified position
     *
     * @param index index of an element to return
     * @return element at specified position
     * @throws IndexOutOfBoundsException if index is out of bounds: [index] < 0 or [index] >= [size]
     */
    public fun get(index: Int): T
}

/**
 * Creates and empty [CompactList] with specified initial size.
 * Returned instance is not thread safe.
 *
 * In addition, if [T] is [Int] underlying implementation will use more compact way of storing elements.
 *
 * @param T type of elements in the list
 * @param initialSize initial size of the list
 * @throws IllegalArgumentException if specified size is negative
 */
public inline fun <reified T> newCompactList(initialSize: Int = 16): CompactList<T> {
    return newCompactList(initialSize, T::class)
}

/**
 * Support function to inline less code on the user side
 */
@PublishedApi
internal fun <T> newCompactList(initialSize: Int, cls: KClass<in T & Any>): CompactList<T> {
    require(initialSize >= 0) { "Initial size should be not negative, but was: $initialSize" }
    @Suppress("UNCHECKED_CAST")
    return when (cls) {
        Int::class -> newIntCompactList(initialSize) as CompactList<T>
        else -> newAnyCompactList(initialSize)
    }
}

internal const val DEFAULT_SIZE = 16
