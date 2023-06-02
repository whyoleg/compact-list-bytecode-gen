package dev.whyoleg.testtask

import kotlin.test.*

class AnyIntCompactListCompatibilityTest : IntCompactListCompatibilityTest(::newAnyCompactList)
class BytecodeIntCompactListCompatibilityTest : IntCompactListCompatibilityTest(::newIntCompactList)
class TestIntCompactListCompatibilityTest : IntCompactListCompatibilityTest(::TestIntCompactList)

abstract class IntCompactListCompatibilityTest(private val create: (Int) -> CompactList<Int>) {

    @Test
    fun testIndexOutOfBoundWithEmptyList() {
        val list = create(1)
        val cause = assertFailsWith<IndexOutOfBoundsException> {
            list.get(0)
        }
        assertEquals("Index 0 out of bounds for size 0", cause.message)
    }

    @Test
    fun testIndexOutOfBoundWithPrefilledList() {
        val list = create(8)
        repeat(5) {
            list.add(it)
        }
        val cause = assertFailsWith<IndexOutOfBoundsException> {
            list.get(7)
        }
        assertEquals("Index 7 out of bounds for size 5", cause.message)
    }

    @Test
    fun testZeroSizeResize() {
        val list = create(0)
        assertEquals(0, list.size)
        list.add(5)
        assertEquals(1, list.size)
        assertEquals(5, list.get(0))
    }

    @Test
    fun testDefaultSizeResize() {
        // will resize: 16 -> 32 -> 64
        val list = create(16)
        assertEquals(0, list.size)
        repeat(33) {
            list.add(it)
            assertEquals(it + 1, list.size)
        }
        repeat(33) {
            assertEquals(it, list.get(it))
        }
    }

    @Test
    fun testCustomSizeResize() {
        // will resize: 3 -> 6 -> 12 -> 24
        val list = create(3)
        assertEquals(0, list.size)
        repeat(20) {
            list.add(it)
            assertEquals(it + 1, list.size)
        }
        repeat(20) {
            assertEquals(it, list.get(it))
        }
    }
}

class CompactListTest : IntCompactListCompatibilityTest(::newCompactList) {
    @Test
    fun testNegativeCapacity() {
        val cause = assertFailsWith<IllegalArgumentException> {
            newCompactList<Int>(-1)
        }
        assertEquals("Initial size should be not negative, but was: -1", cause.message)

        val cause2 = assertFailsWith<IllegalArgumentException> {
            newCompactList<String>(-1)
        }
        assertEquals("Initial size should be not negative, but was: -1", cause2.message)
    }

    @Test
    fun testSimple() {
        val list = newCompactList<String>()
        assertEquals(0, list.size)
        list.add("hello")
        assertEquals(1, list.size)
        val value = list.get(0)
        assertEquals("hello", value)
    }
}
