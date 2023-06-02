@file:JvmMultifileClass
@file:JvmName("CompactListKt")

package dev.whyoleg.testtask

import kotlin.reflect.*

public interface CompactList<T> {
    public val size: Int
    public fun add(value: T)
    public fun get(index: Int): T
}

public inline fun <reified T> newCompactList(initialSize: Int = 16): CompactList<T> {
    return newCompactList(initialSize, T::class)
}

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
