package com.github.markaalvaro.guava.extensions

import com.google.common.collect.ArrayListMultimap
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MutableListMultimapTest {

    @Test
    fun `test set and plus operators`() {
        val multimap = mutableListMultimapOf(
            "a" to 1,
            "a" to 2,
            "b" to 3
        )

        multimap["b"] = listOf(10)
        multimap["c"] = listOf(4, 5, 6)
        multimap += "a" to 5
        multimap += listOf("d" to 5, "d" to 8)

        val expected = ArrayListMultimap.create<String, Int>()
        expected.putAll("a", listOf(1, 2, 5))
        expected.put("b", 10)
        expected.putAll("c", listOf(4, 5, 6))
        expected.putAll("d", listOf(5, 8))

        assertEquals(expected, multimap)
    }
}