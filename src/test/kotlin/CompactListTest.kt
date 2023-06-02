package dev.whyoleg.testtask

import kotlin.test.*

class CompactListTest {
    @Test
    fun testNegativeCapacityInt() {
        val cause = assertFailsWith<IllegalArgumentException> {
            newCompactList<Int>(-1)
        }
        assertEquals("Initial size should be not negative, but was: -1", cause.message)
    }

    @Test
    fun testNegativeCapacityAny() {
        val cause = assertFailsWith<IllegalArgumentException> {
            newCompactList<String>(-1)
        }
        assertEquals("Initial size should be not negative, but was: -1", cause.message)
    }

    @Test
    fun testStringList() {
        val list = newCompactList<String>()
        assertEquals(0, list.size)
        list.add("hello")
        assertEquals(1, list.size)
        val value = list.get(0)
        assertEquals("hello", value)
    }
}
