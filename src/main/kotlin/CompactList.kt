package dev.whyoleg.testtask

public interface CompactList<T> {
    public val size: Int
    public fun add(value: T)
    public fun get(index: Int): T
}

public inline fun <reified T> newCompactList(initialSize: Int = 16): CompactList<T> {
    require(initialSize >= 0) { "Initial size should be not negative, but was: $initialSize" }
    @Suppress("UNCHECKED_CAST")
    return when (T::class) {
        Int::class -> newIntCompactList(initialSize) as CompactList<T>
        else -> newAnyCompactList(initialSize)
    }
}

internal const val DEFAULT_SIZE = 16
