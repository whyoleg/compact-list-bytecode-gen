package dev.whyoleg.testtask

import kotlin.test.Test
import kotlin.test.assertEquals

class CompactListTest {
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
