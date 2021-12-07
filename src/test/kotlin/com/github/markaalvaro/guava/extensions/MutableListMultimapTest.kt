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