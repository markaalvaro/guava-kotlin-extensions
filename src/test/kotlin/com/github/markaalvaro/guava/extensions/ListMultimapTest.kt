/**
 * Copyright 2021-present Mark Alvaro. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.markaalvaro.guava.extensions

import com.google.common.collect.ImmutableListMultimap
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.UnsupportedOperationException
import kotlin.test.assertFailsWith

internal class ListMultimapTest {

    @Test
    fun `test buildListMultimap`() {
        val multimap = buildListMultimap<String, Int> {
            put("a", 1)
            putAll("b", listOf(2, 3, 4))
            remove("b", 3)
        }

        val expected = ImmutableListMultimap.builder<String, Int>()
            .put("a", 1)
            .putAll("b", listOf(2, 4))
            .build()

        assertEquals(expected, multimap)
    }

    @Test
    fun `test get`() {
        val multimap = listMultimapOf("a" to 1, "b" to 2, "b" to 4)

        assertEquals(listOf(1), multimap["a"])
        assertEquals(listOf(2, 4), multimap["b"])
    }

    @Test
    fun `test set`() {
        val multimap = listMultimapOf("a" to 1, "b" to 2, "b" to 4)

        assertFailsWith<UnsupportedOperationException> { multimap["c"] = listOf(5, 6) }
    }

    @Test
    fun `test in`() {
        val multimap = listMultimapOf("a" to 1, "b" to 2, "b" to 4)

        assertTrue("a" to 1 in multimap)
        assertTrue("b" to 2 in multimap)
        assertTrue("a" to 2 !in multimap)
        assertTrue("b" to 1 !in multimap)
        assertTrue("c" to 5 !in multimap)
    }

    @Test
    fun `test plus`() {
        val multimap = listMultimapOf("a" to 1, "b" to 2, "b" to 4)
        val newMultimap = multimap + ("c" to 5)

        val expected = ImmutableListMultimap.builder<String, Int>()
            .put("a", 1)
            .putAll("b", listOf(2, 4))
            .put("c", 5)
            .build()
        assertEquals(expected, newMultimap)
        assertTrue("c" to 5 !in multimap)
    }

    @Test
    fun `test plus many`() {
        val multimap = listMultimapOf("a" to 1, "b" to 2, "b" to 4)
        val newMultimap = multimap + listOf("c" to 5, "c" to 6)

        val expected = ImmutableListMultimap.builder<String, Int>()
            .put("a", 1)
            .putAll("b", listOf(2, 4))
            .putAll("c", listOf(5, 6))
            .build()
        assertEquals(expected, newMultimap)
        assertTrue("c" to 5 !in multimap)
        assertTrue("c" to 6 !in multimap)
    }
}