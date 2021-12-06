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

// Aliases

/**
 * Alias for [com.google.common.collect.ListMultimap] to standardize naming with Kotlin collections
 * API and reduce number of imports to use this library.
 */
typealias MutableListMultimap<K, V> = com.google.common.collect.ListMultimap<K, V>

// Operators

/**
 * Same as [com.google.common.collect.ListMultimap.replaceValues], but adds support for `set` operator syntax.
 */
operator fun <K, V> MutableListMultimap<K, V>.set(key: K, values: Collection<V>) : MutableList<V> = this.replaceValues(key, values)

/**
 * Makes a [MutableListMultimap] copy and adds the passed-in [Pair].
 *
 * @param pair the pair representing a key-value mapping to add to the copied multimap
 * @return the new mutable multimap with the added key-value mapping
 */
operator fun <K, V> MutableListMultimap<K, V>.plus(pair: Pair<K, V>) : MutableListMultimap<K, V> {
    val copy = toMutableListMultimap()
    copy[pair.first] = listOf(pair.second)
    return copy
}

/**
 * Makes a [MutableListMultimap] copy and adds the passed-in [Pair]s.
 *
 * @param pairs the [Pair]s to add to the copy of this multimap
 * @return a mutable copy of this multimap with the specified [Pair]s added
 */
operator fun <K, V> MutableListMultimap<K, V>.plus(pairs: List<Pair<K, V>>) : MutableListMultimap<K, V> {
    val copy = toMutableListMultimap()
    pairs.forEach { pair -> copy[pair.first] = listOf(pair.second) }
    return copy
}

// Creation Methods

/**
 * Returns a mutable [ListMultimap] with mappings given by the passed-in [Pair]s applied.
 *
 * @param pairs the [Pair]s to add to the created multimap
 * @return a mutable multimap with the specified [Pair]s added
 */
fun <K, V> mutableListMultimapOf(vararg pairs: Pair<K, V>) : MutableListMultimap<K, V> {
    val multimap = ArrayListMultimap.create<K, V>()
    pairs.forEach { (key, value) -> multimap.put(key, value) }
    return multimap
}
