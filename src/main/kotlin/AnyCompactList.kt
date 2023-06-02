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
        if (size >= values.size) {
            val newSize = when {
                // if `initialSize` was 0, we resize to some default=16
                values.isEmpty() -> 16
                // if not, double the size of underlying array
                else -> values.size * 2
            }
            // if overflowed - fail
            if (newSize < 0) throw OutOfMemoryError("Required size exceeds implementation limit")
            values = values.copyOf(newSize)
        }
        values[size] = value
        size += 1
    }

    override fun get(index: Int): T {
        if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds for size $size")

        @Suppress("UNCHECKED_CAST")
        return values[index] as T
    }
}
