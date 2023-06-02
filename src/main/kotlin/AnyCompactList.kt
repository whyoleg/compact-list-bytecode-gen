@file:JvmMultifileClass
@file:JvmName("CompactListKt")

package dev.whyoleg.testtask

@PublishedApi
internal fun <T> newAnyCompactList(initialSize: Int): CompactList<T> = AnyCompactList(initialSize)

internal class AnyCompactList<T>(initialSize: Int) : CompactList<T> {
    // size is always <= values.size
    override var size: Int = 0
        private set

    @Suppress("UNCHECKED_CAST")
    private var values = arrayOfNulls<Any?>(initialSize) as Array<T?>

    override fun add(value: T) {
        // check if we need to resize `values` array
        if (size >= values.size) {
            val newSize = when {
                // if `initialSize` was 0, we resize to some default=16
                values.isEmpty() -> DEFAULT_SIZE
                // if not, double the size of underlying array
                else -> values.size * 2
            }
            // if overflowed - fail with OOM
            if (newSize < 0) throw OutOfMemoryError("Required size exceeds implementation limit")
            values = values.copyOf(newSize)
        }
        // save value and increment size
        values[size] = value
        size += 1
    }

    override fun get(index: Int): T {
        if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds for size $size")

        // we always sure at this point that there is a value with specified index, and it's of correct type
        @Suppress("UNCHECKED_CAST")
        return values[index] as T
    }
}
