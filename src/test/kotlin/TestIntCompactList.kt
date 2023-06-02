package dev.whyoleg.testtask

/**
 * This class represents an idea of which bytecode we should generate.
 * Underlying implementation is the same as in [AnyCompactList] except that [Array] replaced by [IntArray]
 */
internal class TestIntCompactList(initialSize: Int) : CompactList<Int> {
    // size is always <= values.size
    override var size: Int = 0
        private set

    private var values = IntArray(initialSize)

    override fun add(value: Int) {
        if (size >= values.size) {
            val newSize = when {
                values.isEmpty() -> 16
                else -> values.size * 2
            }
            if (newSize < 0) throw OutOfMemoryError("Required size exceeds implementation limit")
            values = values.copyOf(newSize)
        }
        values[size] = value
        size += 1
    }

    override fun get(index: Int): Int {
        if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds for size $size")
        return values[index]
    }
}
